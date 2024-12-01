```java
package login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Esta classe representa a interação com o banco de dados, incluindo a validação
 * de usuários através de login e senha. Ela fornece métodos para estabelecer
 * conexões e realizar consultas.
 */
public class User {
    /**
     * Conecta-se ao banco de dados MySQL utilizando o DriverManager.
     * Configura a URL com os dados de acesso, como banco, usuário e senha.
     *
     * @return Connection Retorna a conexão estabelecida com o banco.
     * @throws SQLException Lança uma exceção caso ocorra erro na conexão.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Carrega o driver JDBC e configura a conexão
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jbdc:mysql://127.0.0.1/test?user=lopes&password=123";
        } catch (Exception e) { }

        return conn;
    }

    /**
     * Armazena o nome do usuário obtido na consulta ao banco de dados.
     */
    public String nome = "";
    /**
     * Indica se o usuário foi encontrado na base de dados.
     * 'true' se encontrado, 'false' se não encontrado.
     */
    public boolean result = false;

    /**
     * Valida a existência de um usuário no banco de dados com base no login e senha informados.
     * Executa uma consulta para localizar o nome do usuário correspondente.
     *
     * @param login Login fornecido pelo usuário.
     * @param senha Senha fornecida pelo usuário.
     * @return boolean Retorna 'true' se o usuário existir, ou 'false' caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";
        Connection conn = conectarBD();
        // Monta a consulta SQL para verificar o usuário
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";
        try {
            // Executa a consulta no banco e verifica o resultado
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }
        } catch (Exception e) { }
        return result;
    }
} // fim da class
```