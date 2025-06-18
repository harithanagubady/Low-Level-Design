import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Message {

    private String messageId;
    private String payload;
    private long timestamp;
}
