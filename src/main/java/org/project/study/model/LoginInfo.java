package org.project.study.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor(force = true)
public class LoginInfo implements Serializable {

    @NotNull
    private final Long id;

    @NotNull
    private final String email;

    public LoginInfo(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public static LoginInfo fromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, LoginInfo.class);
        } catch (Exception ex) {
            return null;
        }
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(this);
    }
}
