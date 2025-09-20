package rocks.vivek275.unochamps.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Games {
    @Id
    private String gameName;
    @ManyToOne
    @JoinColumn(name = "season_name", referencedColumnName = "seasonName")
    private Seasons season;
    @Column(nullable = false)
    private List<String> members;
    @Column(nullable = false)
    private List<String> ranks;
    @Column(nullable = false)
    private List<String> points;

}
