package api.models.itemlist;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddItemRequestModel{
    private String uuid;
    private String purchase;
}
