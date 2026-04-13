package api.models.itemlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemRequestModel{
    private String uuid;
    private String purchase;
    private String specification;
}
