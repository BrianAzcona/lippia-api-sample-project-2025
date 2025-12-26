package api.model.time_entry;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TimeInterval{
    public Date start;
    public Date end;
    public String duration;
}
