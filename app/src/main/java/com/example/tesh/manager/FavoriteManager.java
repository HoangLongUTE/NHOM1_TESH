package com.example.tesh.manager;

import com.example.tesh.model.favorite_model;

import java.util.ArrayList;
import java.util.List;

public class FavoriteManager {
    private static List<favorite_model> favoriteList = new ArrayList<>();
    private static OnFavoriteItemRemovedListener removedListener;

    public static List<favorite_model> getFavoriteList() {
        return favoriteList;
    }

    public static void addToFavorites(favorite_model favorite) {
        favoriteList.add(favorite);
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

    public static void setOnFavoriteItemRemovedListener(OnFavoriteItemRemovedListener listener) {
        removedListener = listener;
    }

    public static void removeFromFavorites(String itemId) {
        // Tìm đối tượng có itemId tương ứng và xóa khỏi danh sách yêu thích
        for (favorite_model item : favoriteList) {
            if (item.getTitle_fv().equals(itemId)) {
                favoriteList.remove(item);

                // Thông báo cho lớp lắng nghe nếu có
                if (removedListener != null) {
                    removedListener.onFavoriteItemRemoved(itemId);
                }

                break; // Đảm bảo chỉ xóa một mục nếu có nhiều mục có cùng itemId
            }
        }
    }
}

