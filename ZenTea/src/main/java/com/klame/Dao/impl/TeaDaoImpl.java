package com.klame.Dao.impl;

import com.klame.Dao.TeaDao;
import com.klame.beans.Tea;
import com.klame.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeaDaoImpl implements TeaDao {

    @Override
    public List<Tea> findAllTea() {
        List<Tea> teaList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM tea";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Tea tea = extractTeaFromResultSet(resultSet);
                    teaList.add(tea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teaList;
    }

    @Override
    public List<Tea> findByCategory(String category) {
        List<Tea> teaList = new ArrayList<>();
        Connection connection = null;
        System.out.println(category);
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM tea where TeaCategory = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, category);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Tea tea = extractTeaFromResultSet(resultSet);
                    teaList.add(tea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(teaList.size());
        return teaList;
    }

    @Override
    public List<Tea> findTeaById(int teaId) {
        List<Tea> teaList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM tea WHERE teaId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, teaId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Tea tea = extractTeaFromResultSet(resultSet);
                    teaList.add(tea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teaList;
    }

    @Override
    public List<Tea> findTeaByName(String teaName) {
        List<Tea> teaList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM tea WHERE teaName LIKE ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, "%" + teaName + "%");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Tea tea = extractTeaFromResultSet(resultSet);
                    teaList.add(tea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teaList;
    }

    @Override
    public int addTea(Tea tea) {
        Connection connection = null;
        int i=0;
        try {
            connection = DBUtil.getConnection();
            String sql = "INSERT INTO tea (teaName, teaImg, teaCategory, teaPrice, teaNum, teaProduce, teaIntroduction) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, tea.getTeaName());
                preparedStatement.setString(2, tea.getTeaImg());
                preparedStatement.setString(3, tea.getTeaCategory());
                preparedStatement.setDouble(4, tea.getTeaPrice());
                preparedStatement.setDouble(5, tea.getTeaNum());
                preparedStatement.setString(6, tea.getTeaProduce());
                preparedStatement.setString(7, tea.getTeaIntroduction());

                i=preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public int updateTea(Tea tea) {
        Connection connection = null;
        int i=0;
        try {
            connection = DBUtil.getConnection();
            String sql = "UPDATE tea SET teaName=?, teaCategory=?, teaPrice=?, teaNum=?, teaProduce=?, teaIntroduction=? WHERE teaId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, tea.getTeaName());
                preparedStatement.setString(2, tea.getTeaCategory());
                preparedStatement.setDouble(3, tea.getTeaPrice());
                preparedStatement.setDouble(4, tea.getTeaNum());
                preparedStatement.setString(5, tea.getTeaProduce());
                preparedStatement.setString(6, tea.getTeaIntroduction());
                preparedStatement.setInt(7, tea.getTeaId());

                i=preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public int deleteTea(int teaId) {
        Connection connection = null;
        int i=0;
        try {
            connection = DBUtil.getConnection();
            String sql = "DELETE FROM tea WHERE teaId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, teaId);
                i=preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    DBUtil.freeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    private Tea extractTeaFromResultSet(ResultSet resultSet) throws SQLException {
        Tea tea = new Tea();
        tea.setTeaId(resultSet.getInt("teaId"));
        tea.setTeaName(resultSet.getString("teaName"));
        tea.setTeaImg(resultSet.getString("teaImg"));
        tea.setTeaCategory(resultSet.getString("teaCategory"));
        tea.setTeaPrice(resultSet.getDouble("teaPrice"));
        tea.setTeaNum(resultSet.getDouble("teaNum"));
        tea.setTeaProduce(resultSet.getString("teaProduce"));
        tea.setTeaIntroduction(resultSet.getString("teaIntroduction"));
        return tea;
    }
}
