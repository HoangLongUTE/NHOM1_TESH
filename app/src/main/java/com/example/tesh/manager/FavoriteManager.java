package com.example.tesh.manager;

import com.example.tesh.fragment.FragmentFavorite;
import com.example.tesh.model.favorite_model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FavoriteManager {
    private static List<favorite_model> favoriteList = new ArrayList<>();
    private static OnFavoriteItemRemovedListener removedListener;

    public static List<favorite_model> getFavoriteList() {
        return favoriteList;
    }

    public static void addToFavorites(favorite_model favorite) {
        if (!checkIfItemExists(favorite)) {
            favoriteList.add(favorite);

            // Thông báo cho lớp lắng nghe nếu có
            if (removedListener != null) {
                removedListener.onFavoriteItemRemoved(favorite.getTitle_fv());
            }
        }
    }

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

    public static void removeFromFavorites(String itemId) {
        // Tìm đối tượng có itemId tương ứng và xóa khỏi danh sách yêu thích
        favorite_model itemToRemove = null;
        for (favorite_model item : favoriteList) {
            if (item.getTitle_fv().equals(itemId)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            favoriteList.remove(itemToRemove);

            // Thông báo cho lớp lắng nghe nếu có
            if (removedListener != null) {
                removedListener.onFavoriteItemRemoved(itemId);
            }
        }
    }
    public static void updateFavoritesList(List<favorite_model> updatedList) {
        favoriteList.clear();
        favoriteList.addAll(updatedList);

        // Thông báo cho lớp lắng nghe nếu có
        if (removedListener != null) {
            removedListener.onFavoriteItemRemoved(null); // hoặc có thể truyền một giá trị khác để phân biệt
        }
    }
}
