package org.example.model.DAO;

import org.example.model.entities.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void create(Paciente paciente) {
        String query = "INSERT INTO paciente (nome, sexo, cpf_paciente, codigo_cid, id_medico) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getSexo());
            ps.setString(3, paciente.getCpf());
            ps.setString(4, paciente.getCid());
            ps.setInt(5, paciente.getIdMedico());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paciente> read() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT nome, sexo, cpf_paciente, codigo_cid, id_medico FROM paciente";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getString("nome"),
                        rs.getString("sexo"),
                        rs.getString("cpf_paciente"),
                        rs.getString("codigo_cid"),
                        rs.getInt("id_medico")
                );
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacientes;
    }

    public void update(Paciente paciente) {
        String query = "update paciente set nome = ?, sexo = ? WHERE cpf_paciente = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getSexo());
            ps.setString(3, paciente.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String cpf) {
        String query = "DELETE FROM paciente WHERE cpf_paciente = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
