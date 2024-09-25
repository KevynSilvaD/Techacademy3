import com.google.gson.Gson;
import model.Cena;
import model.Item;
import model.Save;
import repository.CenaDAO;
import repository.ItemDAO;
import repository.SaveDAO;
import spark.Spark;

public class Main {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        try {

            Spark.staticFiles.location("/public");


            Save save = SaveDAO.novoJogo();
            String saveJson = gson.toJson(save);


            Spark.get("/", (req, res) -> {
                res.redirect("/index.html");
                return null;
            });


            Spark.get("/cena/:id", (req, res) -> {
                Integer cenaId = Integer.parseInt(req.params(":id"));
                Cena cena = CenaDAO.findCenaById(cenaId);
                res.type("application/json");
                return gson.toJson(cena);
            });


            Spark.post("/comando", (req, res) -> {

                String comando = req.queryParams("comando").toUpperCase();
                int idCenaAtual = Integer.parseInt(req.queryParams("cenaAtual"));


                Item item = ItemDAO.findComandoCorretoByCenaId(idCenaAtual);

                if (item != null && comando.equals(item.getComandoCorreto().toUpperCase())) {

                    res.type("application/json");
                    return gson.toJson(new Response(true, item.getDescricaoPositiva(), item.getIdCenaDestino()));
                } else {

                    res.type("application/json");
                    return gson.toJson(new Response(false, item != null ? item.getDescricaoNegativa() : "Comando não encontrado."));
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static class Response {
        boolean correto;
        String descricao;
        int proximaCena;

        Response(boolean correto, String descricao, int proximaCena) {
            this.correto = correto;
            this.descricao = descricao;
            this.proximaCena = proximaCena;
        }

        Response(boolean correto, String descricao) {
            this.correto = correto;
            this.descricao = descricao;
        }
    }
}
