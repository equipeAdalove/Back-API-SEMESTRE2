package org.example.model.DAO;

import org.example.model.entities.Patologia;

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
    
    public void delete(int cid) {
        String query = "DELETE FROM patologias WHERE codigo_cid = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cid);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
