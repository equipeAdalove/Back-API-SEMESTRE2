package org.example.model.DAO;

import org.example.model.entities.FichaPaciente;
import org.example.model.entities.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FichaPacienteDAO {

    public void create(FichaPaciente fichaPaciente) {
        String query = "INSERT INTO ficha_paciente (id_ficha, cpf_paciente, id_funcionario, observacoes) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, fichaPaciente.getId());
            ps.setString(2, fichaPaciente.getCpfPaciente());
            ps.setInt(3, fichaPaciente.getIdFuncionario());
            ps.setString(4, fichaPaciente.getObservacoes());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FichaPaciente> read() {
        List<FichaPaciente> fichas = new ArrayList<>();
        String sql = "SELECT id_ficha, cpf_paciente, id_funcionario, observacoes, FROM ficha_paciente";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FichaPaciente fichaPaciente = new FichaPaciente(
                        rs.getInt("id_ficha"),
                        rs.getString("cpf_paciente"),
                        rs.getInt("id_funcionario"),
                        rs.getString("observacoes")
                );
                fichas.add(fichaPaciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fichas;
    }

    public void update(FichaPaciente fichaPaciente, int id) {
        String query = "update ficha_paciente set cpf_paciente = ?, id_funcionario = ?, observacoes = ? WHERE id_ficha = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, fichaPaciente.getCpfPaciente());
            ps.setInt(2, fichaPaciente.getIdFuncionario());
            ps.setString(3, fichaPaciente.getObservacoes());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM ficha_paciente WHERE id_ficha = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
