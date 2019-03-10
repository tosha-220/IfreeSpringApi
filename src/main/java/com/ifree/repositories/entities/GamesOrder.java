package com.ifree.repositories.entities;

import static com.ifree.constants.Constants.GAMES_TYPE;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@DiscriminatorValue(GAMES_TYPE)
@EqualsAndHashCode(callSuper = true)
class GamesOrder extends Order {

  @Column
  private boolean multiplayer;
  @Column(name = "is_online")
  private boolean isOnline;
  @Column(name = "system_requirements")
  private String systemRequirements;

}
