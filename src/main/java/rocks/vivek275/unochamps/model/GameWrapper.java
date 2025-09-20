package rocks.vivek275.unochamps.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameWrapper {
    String gameName;
    List<String> members;
    List<String> ranks;
    List<String> points;
}
