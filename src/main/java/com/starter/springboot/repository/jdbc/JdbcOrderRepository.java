package com.starter.springboot.repository.jdbc;

import com.starter.springboot.model.Ingredient;
import com.starter.springboot.model.Taco;
import com.starter.springboot.model.TacoOrder;
import com.starter.springboot.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcOrderRepository implements OrderRepository {
    private JdbcOperations jdbcOperations;

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory statementCreatorFactory = new PreparedStatementCreatorFactory(
                "insert into Taco_Order (delivery_name, delivery_street, delivery_city, " +
                        "delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) " +
                        "values (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);
        statementCreatorFactory.setReturnGeneratedKeys(true);
        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreator statementCreator = statementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        tacoOrder.getDeliveryName(),
                        tacoOrder.getDeliveryStreet(),
                        tacoOrder.getDeliveryCity(),
                        tacoOrder.getDeliveryState(),
                        tacoOrder.getDeliveryZip(),
                        tacoOrder.getCcNumber(),
                        tacoOrder.getCcExpiration(),
                        tacoOrder.getCcCVV(),
                        tacoOrder.getPlacedAt()
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(statementCreator, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        tacoOrder.setId(orderId);

        List<Taco> tacos = tacoOrder.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return tacoOrder;
    }

    private long saveTaco(long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory statementCreatorFactory = new PreparedStatementCreatorFactory(
                "insert into Taco (name, taco_order, taco_order_key, created_at) values (?,?,?,?)",
                Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.TIMESTAMP);
        statementCreatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator statementCreator = statementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        orderId,
                        orderKey,
                        taco.getCreatedAt()
                )
        );
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(statementCreator, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        saveIngredients(tacoId, taco.getIngredients());
        return tacoId;
    }

    private void saveIngredients(long tacoId, List<Ingredient> ingredients) {
        int key = 0;
        for (Ingredient ingredient: ingredients){
            jdbcOperations.update("insert into Ingredient_Ref (ingredient, taco, taco_key) values (?,?,?)",
                    ingredient.getId(), tacoId, ++key);
        };

    }
}
