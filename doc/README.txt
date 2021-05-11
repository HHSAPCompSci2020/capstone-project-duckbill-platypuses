Homestead Chase
Authors: Ido Ofek, Itai Reingold Nutman, Ophir Peleg
Revision: May 6, 2021


Introduction: 
What does the program do - the program is an interactive, single player, 2D game, in which the player goes through different Homestead classrooms, and answers multiple choice questions of different school subjects, to progress toward the final goal of reaching the i5 classroom and pressing the golden buzzer.
Problem we are solving - For most people, it is boring to learn and study. Therefore, we created this resource that will provide information in a fun and addictive way. 
Story - You wake up after falling asleep in Mr. F’s class to find the other students have turned into zombies. You are presented with a map of the school, where there is one room (i5) that has a red x on it, and you can not go in it. You are told you must complete the questions in the other rooms, before entering the i5 room. In the i5 room you can save everyone and turn them back into a human. The issue is, the zombies want to eat your brains and will try to catch up for you. Thus, when you enter a room, all the zombies in that room will wake up, one at a time (every few seconds another will appear), and chase you. Before the zombies catch up to you and turn you into a zombie, you must read the question and answers on the top of the screen, and choose the best answer. If you get turned into a zombie, you get sent back to the start and your progress is reset. Then you must walk to the answer choice and select it. Try to be quick, and try to be correct!
What are the rules - Navigate, moving up, down, left, right, with the up key, down key, left key, and right key, respectively, through the Homestead map. When you want to enter a room, stand on the icon of the door for that room. Once in the room, read the question and answer choices. Then try to pick the best answer. Once you did that, walk to the answer choice you picked in the room and stand on it to select it. Zombies will, one by one, start to appear and chase you the second you first entered the room. If the zombies catch you, before you select a choice, you lose the game. If you select a wrong choice, you get 1 more chance to select another answer (by walking to it). If the second selection is also wrong, you lose. In each new classroom, your chances (lives) reset to 2 again, and the zombies reset to 0. 
What is the goal - The goal is to answer the questions in each classroom that is unlocked. Once you do that you will be able to enter the final classroom, where you must hit the TURN ZOMBIES TO HUMANS button to save everyone.
Who will use our program - Students looking for a fun way to study. The game will allow people to enjoy a nice challenging game, while simultaneously learning. 


What are the primary features of your program? - 


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
* The question + answer - Text at the top of the screen which will explain the questions and provide the answer choices. 
* A, B, C, D, etc, (Answer letters)- The letters in the classroom which you will need to walk to and hover over to select. 
* Zombies infecting you: Zombies that will chase you in the classrooms while you need to answer the questions. 
* The person: A movable person that needs to navigate through the map, and within the class to answer the questions. 
* The map - The map of the school the person should move through and enter classrooms


Want-to-have Features:
*  A transition when you change rooms.
*  A progress bar showing how many classrooms you have done and need to do still. 
*  When you reach i5 you need to face a final boss, which will be a free response question that will pop up instead of nothing that will force a challenging final boss. 
*  Each classroom has a different image. So the history might have a globe, and the math might have trig functions on the walls. 
* More dialogue - Before a challenge appears a teacher can say something fitting. For example, the math room might say “Remember: No Calculators” or “You only get root 9 minutes for the questions”. For intro to java, “Remember to smile, it brings oxygen to your brain”
* More images for zombies


Stretch Features:
*  Save the highest player score - the program would be able to save the time it took a person to complete the entire program, and present the highest score to other players.
*  Players can add their own questions, and transform the game into a quizlet type program, where the players can study in a fun way for specific tests. The game would become more generic, and not divided into subjects based on homestead buildings.
*  Add a multiplayer option to the game, in which players compete against each other to complete the game the fastest.
* More animations: For example, when a zombie kills you or injures you, an animation screen comes showing you dying or becoming more infected  (Like among us). When you hit the final button, an animation showing all students turning back into humans appears. 
* The images to change directions depending on how the character us moving




Class List:
Main - Run the program
Map - The map of the program, with all the classes and the locked final class. 
Classroom - Has properties of each room, mainly the question/answer object. Also, any other images needed (globe for history for example). 
Person (superclass) - Fields: x, y, image.
Player - extends person, user controlled movement. 
Zombie - extends person, has code that chases the person. 
DrawingSurface- Connects all other classes, to make the course and the game. 
Problem - A class that holds a question and possible answers. Also holds the amount of time in between zombies.


Credits:
Itai: Person (superclass), Zombie,Drawing Surface
Ido: Player, Classroom
Ophir: Problem, Main, Map
External Credit: 
* We used the processing library for graphics. 
* Mr. Shelby’s code was used as a template for the Main class.