package com.charlesfrost.blb.validators;

import com.charlesfrost.blb.models.Match;
import com.charlesfrost.blb.models.Team;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TeamsValidator implements ConstraintValidator<ValidateTeams, Match> {
   @Override
   public boolean isValid(Match match, ConstraintValidatorContext constraintValidatorContext) {
      return !match.getAwayTeam().equals(match.getHomeTeam());
   }
}
