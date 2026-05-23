/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Category;
import hr.algebra.model.Mission;
import hr.algebra.model.NasaImage;
import hr.algebra.model.Photographer;
import hr.algebra.model.Role;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author kvash
 */
public class SqlRepository implements Repository {

    private static final String ID = "Id";
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String ID_MISSION = "IdMission";
    private static final String ID_PHOTOGRAPHER = "IdPhotographer";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String ID_NASA_IMAGE = "IdNasaImage";
    private static final String ID_CATEGORY = "IdCategory";
    private static final String USERNAME = "Username";
    private static final String PASSWORDHASH = "PasswordHash";
    private static final String ROLE = "Role";

    private static final String CREATE_NASA_IMAGE = "{ CALL createNasaImage (?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_NASA_IMAGE = "{ CALL updateNasaImage (?,?,?,?,?,?,?,?) }";
    private static final String DELETE_NASA_IMAGE = "{ CALL deleteNasaImage (?) }";
    private static final String SELECT_NASA_IMAGE = "{ CALL selectNasaImage (?) }";
    private static final String SELECT_NASA_IMAGES = "{ CALL selectNasaImages }";

    private static final String CREATE_CATEGORY = "{ CALL createCategory (?) }";
    private static final String UPDATE_CATEGORY = "{ CALL updateCategory (?,?) }";
    private static final String DELETE_CATEGORY = "{ CALL deleteCategory (?) }";
    private static final String SELECT_CATEGORIES = "{ CALL selectCategories }";
    private static final String SELECT_CATEGORY = "{ CALL selectCategory (?) }";

    private static final String CREATE_MISSION = "{ CALL createMission (?,?) }";
    private static final String UPDATE_MISSION = "{ CALL updateMission (?,?,?) }";
    private static final String DELETE_MISSION = "{ CALL deleteMission (?) }";
    private static final String SELECT_MISSIONS = "{ CALL selectMissions }";
    private static final String SELECT_MISSION = "{ CALL selectMission (?) }";

    private static final String CREATE_PHOTOGRAPHER = "{ CALL createPhotographer (?,?) }";
    private static final String UPDATE_PHOTOGRAPHER = "{ CALL updatePhotographer (?,?,?) }";
    private static final String DELETE_PHOTOGRAPHER = "{ CALL deletePhotographer (?) }";
    private static final String SELECT_PHOTOGRAPHERS = "{ CALL selectPhotographers }";
    private static final String SELECT_PHOTOGRAPHER = "{ CALL selectPhotographer (?) }";

    private static final String ADD_CATEGORY_TO_IMAGE = "{ CALL addCategoryToImage (?,?) }";
    private static final String REMOVE_CATEGORY_FROM_IMAGE = "{ CALL removeCategoryFromImage (?,?) }";
    private static final String SELECT_CATEGORIES_FOR_IMAGE = "{ CALL selectCategoriesForImage (?) }";

    private static final String CREATE_USER = "{ CALL createUser (?,?,?) }";
    private static final String SELECT_USER_BY_USERNAME = "{ CALL selectUserByUsername (?) }";
    
    private static final String DELETE_ALL = "{ CALL deleteAll }";

    @Override
    public int createNasaImage(NasaImage image) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                // get a connection to the db
                Connection con = dataSource.getConnection();
                // prepare the call ("get ready for this procedure")
                 CallableStatement stmt = con.prepareCall(CREATE_NASA_IMAGE)) {

            // bind input values to ? placeholders
            stmt.setString(TITLE, image.getTitle());
            stmt.setString(LINK, image.getLink());
            stmt.setString(DESCRIPTION, image.getDescription());
            stmt.setString(PICTURE_PATH, image.getPicturePath());
            stmt.setString(PUBLISHED_DATE, image.getPublishedDate().format(NasaImage.DATE_FORMATTER));

            if (image.getIdMission() != null) {
                stmt.setInt(ID_MISSION, image.getIdMission());
            } else {
                stmt.setNull(ID_MISSION, Types.INTEGER);
            }

            if (image.getIdPhotographer() != null) {
                stmt.setInt(ID_PHOTOGRAPHER, image.getIdPhotographer());
            } else {
                stmt.setNull(ID_PHOTOGRAPHER, Types.INTEGER);
            }

            // set which parameter is output
            stmt.registerOutParameter(ID, Types.INTEGER);

            stmt.executeUpdate(); // actually execute
            return stmt.getInt(ID); // read the output parameter back
        }
    }

    @Override
    public void createNasaImages(List<NasaImage> images) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_NASA_IMAGE)) {

            stmt.registerOutParameter(ID, Types.INTEGER);

            for (NasaImage image : images) {
                stmt.setString(TITLE, image.getTitle());
                stmt.setString(LINK, image.getLink());
                stmt.setString(DESCRIPTION, image.getDescription());
                stmt.setString(PICTURE_PATH, image.getPicturePath());
                stmt.setString(PUBLISHED_DATE, image.getPublishedDate().format(NasaImage.DATE_FORMATTER));

                if (image.getIdMission() != null) {
                    stmt.setInt(ID_MISSION, image.getIdMission());
                } else {
                    stmt.setNull(ID_MISSION, Types.INTEGER);
                }

                if (image.getIdPhotographer() != null) {
                    stmt.setInt(ID_PHOTOGRAPHER, image.getIdPhotographer());
                } else {
                    stmt.setNull(ID_PHOTOGRAPHER, Types.INTEGER);
                }

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void updateNasaImage(int id, NasaImage data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_NASA_IMAGE)) {

            stmt.setInt(ID, id);
            stmt.setString(TITLE, data.getTitle());
            stmt.setString(LINK, data.getLink());
            stmt.setString(DESCRIPTION, data.getDescription());
            stmt.setString(PICTURE_PATH, data.getPicturePath());
            stmt.setString(PUBLISHED_DATE, data.getPublishedDate().format(NasaImage.DATE_FORMATTER));

            if (data.getIdMission() != null) {
                stmt.setInt(ID_MISSION, data.getIdMission());
            } else {
                stmt.setNull(ID_MISSION, Types.INTEGER);
            }
            if (data.getIdPhotographer() != null) {
                stmt.setInt(ID_PHOTOGRAPHER, data.getIdPhotographer());
            } else {
                stmt.setNull(ID_PHOTOGRAPHER, Types.INTEGER);
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteNasaImage(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_NASA_IMAGE)) {
            stmt.setInt(ID, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<NasaImage> selectNasaImage(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_NASA_IMAGE)) {
            stmt.setInt(ID, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new NasaImage(
                            rs.getInt(ID),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), NasaImage.DATE_FORMATTER),
                            rs.getObject(ID_MISSION) != null ? rs.getInt(ID_MISSION) : null,
                            rs.getObject(ID_PHOTOGRAPHER) != null ? rs.getInt(ID_PHOTOGRAPHER) : null));
                }
            }
            return Optional.empty();
        }
    }

    @Override
    public List<NasaImage> selectNasaImages() throws Exception {
        List<NasaImage> images = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_NASA_IMAGES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                images.add(new NasaImage(
                        rs.getInt(ID),
                        rs.getString(TITLE),
                        rs.getString(LINK),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        LocalDateTime.parse(rs.getString(PUBLISHED_DATE), NasaImage.DATE_FORMATTER),
                        rs.getObject(ID_MISSION) != null ? rs.getInt(ID_MISSION) : null,
                        rs.getObject(ID_PHOTOGRAPHER) != null ? rs.getInt(ID_PHOTOGRAPHER) : null));
            }
        }
        return images;
    }

    @Override
    public void createCategory(Category category) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_CATEGORY)) {
            stmt.setString(NAME, category.getName());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateCategory(int id, Category data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_CATEGORY)) {
            stmt.setInt(ID, id);
            stmt.setString(NAME, data.getName());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCategory(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_CATEGORY)) {
            stmt.setInt(ID, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Category> selectCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_CATEGORIES);
                ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                categories.add(new Category(rs.getInt(ID), rs.getString(NAME)));
            }
        }
        return categories;
    }
    
    @Override
    public Optional<Category> selectCategory(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_CATEGORY)) {
            stmt.setInt(ID, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Category(rs.getInt(ID), rs.getString(NAME)));
                }
            }
            return Optional.empty();
        }
    }

    @Override
    public void createMission(Mission mission) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MISSION)) {
            stmt.setString(NAME, mission.getName());
            stmt.setString(DESCRIPTION, mission.getDescription());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateMission(int id, Mission data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MISSION)) {
            stmt.setInt(ID, id);
            stmt.setString(NAME, data.getName());
            stmt.setString(DESCRIPTION, data.getDescription());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMission(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MISSION)) {
            stmt.setInt(ID, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Mission> selectMissions() throws Exception {
        List<Mission> missions = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MISSIONS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                missions.add(new Mission(rs.getInt(ID), rs.getString(NAME), rs.getString(DESCRIPTION)));
            }
        }
        return missions;
    }
    
    @Override
    public Optional<Mission> selectMission(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MISSION)) {
            stmt.setInt(ID, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Mission(rs.getInt(ID), rs.getString(NAME), rs.getString(DESCRIPTION)));
                }
            }
            return Optional.empty();
        }
    }

    @Override
    public void createPhotographer(Photographer photographer) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PHOTOGRAPHER)) {
            stmt.setString(NAME, photographer.getName());
            stmt.setString(SURNAME, photographer.getSurname());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updatePhotographer(int id, Photographer data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_PHOTOGRAPHER)) {
            stmt.setInt(ID, id);
            stmt.setString(NAME, data.getName());
            stmt.setString(SURNAME, data.getSurname());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletePhotographer(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_PHOTOGRAPHER)) {
            stmt.setInt(ID, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Photographer> selectPhotographers() throws Exception {
        List<Photographer> photographers = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PHOTOGRAPHERS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                photographers.add(new Photographer(rs.getInt(ID), rs.getString(NAME), rs.getString(SURNAME)));
            }
        }
        return photographers;
    }
    
    @Override
    public Optional<Photographer> selectPhotographer(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PHOTOGRAPHER)) {
            stmt.setInt(ID, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Photographer(rs.getInt(ID), rs.getString(NAME), rs.getString(SURNAME)));
                }
            }
            return Optional.empty();
        }
    }

    @Override
    public void addCategoryToImage(int idImage, int idCategory) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(ADD_CATEGORY_TO_IMAGE)) {
            stmt.setInt(ID_NASA_IMAGE, idImage);
            stmt.setInt(ID_CATEGORY, idCategory);
            stmt.executeUpdate();
        }
    }

    @Override
    public void removeCategoryFromImage(int idImage, int idCategory) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(REMOVE_CATEGORY_FROM_IMAGE)) {
            stmt.setInt(ID_NASA_IMAGE, idImage);
            stmt.setInt(ID_CATEGORY, idCategory);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Category> selectCategoriesForImage(int idImage) throws Exception {
        List<Category> categories = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_CATEGORIES_FOR_IMAGE)) {

            stmt.setInt(ID_NASA_IMAGE, idImage);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(rs.getInt(ID), rs.getString(NAME)));
                }
            }
        }
        return categories;
    }

    @Override
    public void createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            stmt.setString(USERNAME, user.getUsername());
            stmt.setString(PASSWORDHASH, user.getPasswordHash());
            stmt.setString(ROLE, user.getRole().name());
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<User> selectUserByUsername(String username) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER_BY_USERNAME)){
            stmt.setString(USERNAME, username);
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    return Optional.of(new User(
                            rs.getInt(ID),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORDHASH),
                            Role.from(rs.getString(ROLE)).get()));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteAll() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ALL);){
            stmt.executeUpdate();
        }
    }

}
