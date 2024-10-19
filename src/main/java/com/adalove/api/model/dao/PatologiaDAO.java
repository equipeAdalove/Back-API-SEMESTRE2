package com.adalove.api.model.dao;

import com.adalove.api.model.entities.Patologia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatologiaDAO {

    public void create(Patologia patologia) {
        String query = "INSERT INTO patologias (codigo_cid, grau, nome) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, patologia.getCid());
            ps.setInt(2, patologia.getGrau());
            ps.setString(3, patologia.getNome());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Patologia> read() {
        List<Patologia> patologias = new ArrayList<>();
        String sql = "SELECT codigo_cid, grau, nome FROM patologias";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patologia patologia = new Patologia(
                        rs.getString("codigo_cid"),
                        rs.getInt("grau"),
                        rs.getString("nome")
                );
                patologias.add(patologia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patologias;
    }

    public List<Patologia> buscarPorNome(String nome) {
        List<Patologia> patologias = new ArrayList<>();
        String query = "SELECT codigo_cid, grau, nome FROM patologias WHERE nome LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Patologia patologia = new Patologia(
                        rs.getString("codigo_cid"),
                        rs.getInt("grau"),
                        rs.getString("nome")
                );
                patologias.add(patologia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patologias;
    }


    public void update(Patologia patologia) {
        String query = "update patologias set grau = ?, nome = ? WHERE codigo_cid = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, patologia.getGrau());
            ps.setString(2, patologia.getNome());
            ps.setString(3, patologia.getCid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String cid) { // Altere o tipo para String
        String query = "DELETE FROM patologias WHERE codigo_cid = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cid); // Usar setString, já que cid é uma String
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
