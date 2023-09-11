package cu.dev.halal.dao;

import cu.dev.halal.dto.FavoriteDTO;
import cu.dev.halal.entity.UserEntity;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FavoritesDAO {
    public JSONObject addFavorite(UserEntity userEntity);
    public JSONObject deleteFavorite(UserEntity userEntity);
    public List<Long> getFavorites(String email);

}