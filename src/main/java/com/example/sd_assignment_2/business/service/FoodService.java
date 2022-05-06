package com.example.sd_assignment_2.business.service;

import com.example.sd_assignment_2.business.model.Food;
import com.example.sd_assignment_2.persistance.FoodRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    private Logger logger = LoggerFactory.getLogger(FoodService.class);

    public void setFoodRepository(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * Adds a new food to the database
     * @param food the food to be added
     */
    public void addFood(Food food){
        foodRepository.save(food);
        logger.info("A new food with id "+food.getId()
                +" was added to the restaurant with id "+food.getRestaurant().getId()
                +" by the admin with id "+food.getRestaurant().getAdmin().getId());
    }

    /**
     * Searches the database for foods that belong to a specific restaurant
     * @param id the id of the restaurant
     * @return a list of the found foods
     */
    public ArrayList<Food> getFoodsByRestaurantId(Long id){
        return foodRepository.getAllByRestaurant_Id(id);
    }

    /**
     * Searches the database for the food item with the given id
     * @param id the id of the food
     * @return the found food
     */
    public Food findById(Long id){
        return foodRepository.findOne(id);
    }

    /**
     * Creates a document containing the foods of a restaurant
     * @param idRestaurant the id of the restaurant
     * @param response the http response to which the document will be attached
     */
    public void createPdfOfRestaurantMenu(Long idRestaurant, HttpServletResponse response){
        ArrayList<Food> foods = getFoodsByRestaurantId(idRestaurant);
        Document document = new Document(PageSize.A4);

        try{
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontTitle.setSize(18);
            Paragraph title = new Paragraph("Menu of the restaurant with id "+idRestaurant,fontTitle);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            Font fontTitleFood = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontTitleFood.setSize(16);
            Font fontFoodInfo = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontFoodInfo.setSize(12);

            for(Food food: foods){
                Paragraph titleFood = new Paragraph(food.getName()+"\n",fontTitleFood);
                titleFood.setAlignment(Paragraph.ALIGN_LEFT);
                document.add(titleFood);

                Paragraph foodInfo = new Paragraph(food.getId()+", "+food.getCategory()+", "+food.getDescription()+", "+food.getPrice()+"\n",fontFoodInfo);
                foodInfo.setAlignment(Paragraph.ALIGN_LEFT);
                document.add(foodInfo);
            }
            document.close();
        }
        catch (Exception e){

        }
    }
}
