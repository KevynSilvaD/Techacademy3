package repository;

import model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ItemDAO {

    public static Item findComandoCorretoByCenaId(int idCenaAtual) throws Exception {
        Connection connection = Mysql.getConnection(); // Supondo que você tenha uma classe Database para obter a conexão
        String sql = "SELECT * FROM itens WHERE id_cena_atual = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idCenaAtual);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Item item = new Item();
            item.setComandoCorreto(rs.getString("comando_correto"));
            item.setDescricaoPositiva(rs.getString("descricao_positiva"));
            item.setDescricaoNegativa(rs.getString("descricao_negativa"));
            item.setIdCenaDestino(rs.getInt("id_cena_destino"));
            return item;
        }
        return null; // Nenhum comando correto encontrado
    }
}
