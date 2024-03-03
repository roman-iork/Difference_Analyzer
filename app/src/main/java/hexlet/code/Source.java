package hexlet.code;

import lombok.Setter;
import lombok.Getter;
import java.io.File;

@Setter
@Getter
public class Source {
    private File fileSource;
    private String fileExtension;
}
