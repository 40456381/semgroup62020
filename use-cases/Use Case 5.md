# USE CASE: 5 Produce a Report of the top N populated countries in a continent where N is provided by the user.


## CHARACTERISTIC INFORMATION

### Goal in Context

*As a user I want to produce a report of the top N populated countries in a continent where N is provided by the user so that the data can be used by the organisation*

### Scope

Company.

### Level

Primary task.

### Preconditions

The database contains the information about the continents their countries and their populations

### Success End Condition

A report is available for user to view.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

The organisation needs this report to be available to users

## MAIN SUCCESS SCENARIO

1. User inputs N
2. Report is created
3. Report details population of countries
4. Report is available to users

## EXTENSIONS

1. **Country does not exist**:
    1. User is notified

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0