import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class GerenteBancoDadosTest {

    @Test
    void conectar() {

        GerenteBancoDados gerente = new GerenteBancoDados();

        try {
            gerente.obterConexao();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}