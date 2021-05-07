package me.devksh930.entity;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Period {
    @Temporal(TemporalType.DATE)
    java.util.Date startDate;
    @Temporal(TemporalType.DATE)
    java.util.Date endDate;
}
