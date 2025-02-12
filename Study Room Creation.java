public class StudyRoom {
    private static Connection conn = DatabaseConnection.getConnection();

    public static boolean createRoom(String roomName, int userId, boolean isPrivate) {
        try {
            String query = "INSERT INTO study_rooms (room_name, created_by, is_private) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, roomName);
            pstmt.setInt(2, userId);
            pstmt.setBoolean(3, isPrivate);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
