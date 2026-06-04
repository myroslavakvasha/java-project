/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.Category;
import hr.algebra.model.Mission;
import hr.algebra.model.NasaImage;
import hr.algebra.model.Photographer;
import hr.algebra.model.User;
import hr.algebra.model.dto.NasaImageExportDto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kvash
 */
public interface Repository {

    int createNasaImage(NasaImage image) throws Exception;
    void createNasaImages(List<NasaImage> images) throws Exception;
    void updateNasaImage(int id, NasaImage data) throws Exception;
    void deleteNasaImage(int id) throws Exception;
    Optional<NasaImage> selectNasaImage(int id) throws Exception;
    List<NasaImage> selectNasaImages() throws Exception;

    void createCategory(Category category) throws Exception;
    void updateCategory(int id, Category data) throws Exception;
    void deleteCategory(int id) throws Exception;
    List<Category> selectCategories() throws Exception;
    Optional<Category> selectCategory(int id) throws Exception;
    
    void createMission(Mission mission) throws Exception;
    void updateMission(int id, Mission data) throws Exception;
    void deleteMission(int id) throws Exception;
    List<Mission> selectMissions() throws Exception;
    Optional<Mission> selectMission(int id) throws Exception;

    void createPhotographer(Photographer photographer) throws Exception;
    void updatePhotographer(int id, Photographer data) throws Exception;
    void deletePhotographer(int id) throws Exception;
    List<Photographer> selectPhotographers() throws Exception;
    Optional<Photographer> selectPhotographer(int id) throws Exception;

    void addCategoryToImage(int idImage, int idCategory) throws Exception;
    void removeCategoryFromImage(int idImage, int idCategory) throws Exception;
    List<Category> selectCategoriesForImage(int idImage) throws Exception;
    void deleteAll() throws Exception;

    void createUser(User user) throws Exception;
    Optional<User> selectUserByUsername(String username) throws Exception;
    
    List<NasaImageExportDto> selectNasaImagesForExport() throws Exception;
    List<String> selectCategoryNamesForImage(int idImage) throws Exception;
}
