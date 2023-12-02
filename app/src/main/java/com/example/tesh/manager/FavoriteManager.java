package com.example.tesh.manager;

import com.example.tesh.model.favorite_model;

import java.util.ArrayList;
import java.util.List;

public class FavoriteManager {
    private static List<favorite_model> favoriteList = new ArrayList<>();

    public static List<favorite_model> getFavoriteList() {
        return favoriteList;
    }

    public static void addToFavorites(favorite_model favorite) {
        favoriteList.add(favorite);
    }

    // Kiểm tra xem một item có trong danh sách yêu thích hay không
    public static boolean checkIfItemExists(favorite_model favorite) {
        for (favorite_model item : favoriteList) {
            if (item.getTitle_fv().equals(favorite.getTitle_fv())) {
                // Mục đã tồn tại trong danh sách yêu thích
                return true;
            }
        }
        // Mục không tồn tại trong danh sách yêu thích
        return false;
    }

    // Triển khai hàm removeFromFavorites
    public static void removeFromFavorites(favorite_model removedItem) {
        favoriteList.remove(removedItem);
    }
}
