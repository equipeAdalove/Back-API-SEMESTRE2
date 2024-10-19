package com.adalove.api.model.dao;

import com.adalove.api.model.entities.FichaPaciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FichaPacienteDAO {

    public void create(FichaPaciente fichaPaciente) {
        String query = "INSERT INTO ficha_paciente (cpf_paciente, id_funcionario, observacoes, data_hora) VALUES (?, ?, ?, NOW())";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, fichaPaciente.getCpfPaciente());
            ps.setInt(2, fichaPaciente.getIdFuncionario());
            ps.setString(3, fichaPaciente.getObservacoes());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FichaPaciente> read() {
        List<FichaPaciente> fichas = new ArrayList<>();
        String sql = "SELECT id_ficha, cpf_paciente, id_funcionario, observacoes, data_hora FROM ficha_paciente";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FichaPaciente fichaPaciente = new FichaPaciente(
                        rs.getInt("id_ficha"),
                        rs.getString("cpf_paciente"),
                        rs.getInt("id_funcionario"),
                        rs.getString("observacoes"),
                        rs.getTimestamp("data_hora").toLocalDateTime());
                fichas.add(fichaPaciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fichas;
    }

    public List<FichaPaciente> buscarPorCPF(String cpf) {
        List<FichaPaciente> fichas = new ArrayList<>();
        String sql = "SELECT id_ficha, cpf_paciente, id_funcionario, observacoes, data_hora FROM ficha_paciente WHERE cpf_paciente LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Adicionando os caracteres curinga '%' para a busca parcial
            stmt.setString(1, "%" + cpf + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    FichaPaciente ficha = new FichaPaciente(
                            rs.getInt("id_ficha"),
                            rs.getString("cpf_paciente"),
                            rs.getInt("id_funcionario"),
                            rs.getString("observacoes"),
                            rs.getTimestamp("data_hora").toLocalDateTime());
                    fichas.add(ficha);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fichas;
    }


    public void update(FichaPaciente fichaPaciente, int id) {
        String query = "UPDATE ficha_paciente SET cpf_paciente = ?, id_funcionario = ?, observacoes = ? WHERE id_ficha = ?";
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
