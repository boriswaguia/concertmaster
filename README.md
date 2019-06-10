[![Build Status](https://travis-ci.org/boriswaguia/concertmaster.svg?branch=master)](https://travis-ci.org/boriswaguia/concertmaster)

# Concertmaster

Fluent java library that makes it easy to write business logics

## Why Concertmaster
As a developer we sometime wants the code to reflect the logic in which we want it to be executed. Concertmaster master
let you abstract the implementation details of your logic, and let you write only what the program should do not how
it should be doing it.


## Example

Here is an example usage of the api where the  business logic is to:

- Create a user
- Send an email to the user if the creation was successful


<code>

    Action createUserActionAs = new CreateUserActionImpl();
    Action sendEmailToUserActionAs = new SendEmailToUserActionImpl();

    // Describe your business logic sequence in an expressive way, in term of state machine
    Set<ActionNode> actionNodes = StateMachine.builder()
            .when(new ActionId("create-user-action-impl"))
                .success(new AbstractId("send-email-to-user-action-impl"))
                .append()
            .when(new ActionId("send-email-to-user-action-impl"))
                .append()
            .getMachine()
            .getActionNodes();
            
    // Populate the service repository
    // The framework will use this later to get services implementations by their ids and execute them
    ActionRepository actionRepository = ActionRepository.init().add(createUserActionAs).add(sendEmailToUserActionAs);
    
    // Let the framework run the whole thing for you, and handle all the possible branchings.
    ActionRunner.instance().run(actionNodes, actionRepository);
        
</code>




## Advantages

Concertmaster provide the following advantages:

- Clean separation between what the program should do and how it should be doing it
- This force you to write a SOLID object oriented code. (By composing independent business logic)
- Clear understanding of what the system should do
