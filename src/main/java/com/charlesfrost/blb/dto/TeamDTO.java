package com.charlesfrost.blb.dto;

import com.charlesfrost.blb.models.Coach;
import com.charlesfrost.blb.models.Player;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class TeamDTO {
    @NotBlank
    @Size(max = 64, min = 2)
    private String name;
    @NotNull
    private LocalDate createDate;
    @DecimalMin("1")
    private Long coachId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }
}
