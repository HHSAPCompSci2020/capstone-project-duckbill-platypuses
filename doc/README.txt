Homestead Chase
Authors: Ido Ofek, Itai Reingold Nutman, Ophir Peleg
Revision: May 23, 2021

Introduction: 
What does the program do - the program is an interactive, single player, 2D game, in which the player goes through different Homestead classrooms, and answers multiple choice questions of different school subjects, to progress toward the final goal of reaching the java classroom and completing the final question.
Problem we are solving - For most people, it is boring to learn and study. Therefore, we created this resource that will provide information in a fun and addictive way. 
Story - You come to school 10 minutes late and you find that all of your classmates have mysteriously been transformed into zombies. You are presented with a map of the school, where you see all of the doors that you can go into. You are told that one door is locked and only once you complete challenges in all of the other rooms you can enter. In the java room you can save everyone and turn them back into a human. The issue is, the zombies want to eat your brains and will try to catch up for you. Thus, when you enter a room, all the zombies in that room will wake up, one at a time (every few seconds another will appear), and chase you. Before the zombies catch up to you and turn you into a zombie, you must read the question and answers on the top of the screen, and think about the right answer. Once you have the right answer, walk over it to select it. 
What are the rules - Navigate, moving up, down, left, right, with the up, down, left, and right key, respectively, through the Homestead map. When you want to enter a room, stand on the icon of the door for that room. Once in the room, read the question and answer choices. Then try to pick the best answer. Once you did that, walk to the answer choice you picked in the room and stand on it to select it. Zombies will, one by one, start to appear and chase you the second you first entered the room. If the zombies catch you, before you select a choice, you lose the game. If you select a wrong choice, you get 1 more chance to select another answer (by walking to it). If the second selection is also wrong, you lose. In each new classroom, your chances (lives) reset to 2 again, and the zombies reset too.
What is the goal - The goal is to answer the questions in each classroom that is unlocked. Once you do that you will be able to enter the final classroom, where you must answer a final question to save everyone.
Who will use our program - Students looking for a fun way to study. The game will allow people to enjoy a nice challenging game, while simultaneously learning. Specifically, this could be for students who use websites like quizlet, or make notecards to study, as they can do a similar thing in a much more fun way.

What are the primary features of your program? - 

Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:
1) The question + answer - Text at the top of the screen which will explain the questions and provide the answer choices. 
2) A, B, C, D, etc, (Answer letters)- The letters in the classroom which you will need to walk to and hover over to select. 
3) Zombies infecting you: Zombies that will chase you in the classrooms while you need to answer the questions. 
4) The person: A movable person that needs to navigate through the map, and within the class to answer the questions. 
5) The map - The map of the school the person should move through and enter classrooms

Want-to-have Features:
1) A transition when you change rooms.
2) A progress bar showing how many classrooms you have done and need to do still. 
3) When you reach java you need to face a final boss, which will be a free response question that will pop up instead of nothing that will force a challenging final boss. 
4) Each classroom has a different image. So the history might have a globe, and the math might have trig functions on the walls. 
5) More dialogue - Before a challenge appears a teacher can say something fitting. For example, the math room might say “Remember: No Calculators” or “You only get root 9 minutes for the questions”. For intro to java, “Remember to smile, it brings oxygen to your brain”
6) More images for zombies

Stretch Features:
1) Save the highest player score - the program would be able to save the time it took a person to complete the entire program, and present the highest score to other players.
2) Players can add their own questions, and transform the game into a quizlet type program, where the players can study in a fun way for specific tests. The game would become more generic, and not divided into subjects based on homestead buildings.
3) Add a multiplayer option to the game, in which players compete against each other to complete the game the fastest.
4) More animations: For example, when a zombie kills you or injures you, an animation screen comes showing you dying or becoming more infected  (Like among us). When you hit the final button, an animation showing all students turning back into humans appears. 
5) The images to change directions depending on how the character is moving


Class List:
1) Main - Run the program
2) Map - The map of the program, with all the classes and the locked final class. 
3) Classroom - Has properties of each room, mainly the question/answer object. Also, any other images needed (globe for history for example). 
4) Person (superclass) - Fields: x, y, image.
5) Player - extends person, user controlled movement. 
6) Zombie - extends person, has code that chases the person. 
7) DrawingSurface- Connects all other classes, to make the course and the game. 
8) Problem - A class that holds a question and possible answers. Also holds the amount of time in between zombies.

Credits:
Itai: The lead classes Itai is responsible for are: Person, Zombie, and Drawing Surface. Itai also worked on debugging the code, which consisted of looking through all classes. Itai also worked on adding the process bar, with the on and off button. Itai also worked on adding the wav sound. Itai wrote and added the instructions panel, button, and text. Itai also added the flickering effect for the final room, and made the final room only be accessible after the other rooms were finished. Itai also wrote the questions for around half the rooms. 
Ido: The lead classes Ido is responsible for are: Player and Classroom. Ido also worked on debugging the code, which consisted of looking through all classes. Ido also worked on creating all the images for the rooms, the characters, the win and loose screens, and the objects in the rooms. 
Ophir: The lead classes Ophir is responsible for are: Problem, Main, and Map. Ophir also worked on debugging which included looking through all classes. Ophir also wrote questions for around half of the rooms. 
External Credit: 
1) We used the processing library for graphics and key detection.  
2) Mr. Shelby’s code was used as a template for the Main class.
3) The movement for the player was from the animation demo.
4) The image, for the off and on button was obtained from: https://www.elharrakfonts.com/2019/06/on-off-icon-button.html
5) Our character’s and rooms were made using a website called Piskel
6) The siren sounds we used is the first siren from this website: https://www.findsounds.com/ISAPI/search.dll?keywords=siren
6) The info button we used was from this website: http://pixelartmaker.com/art/e9c7716c87f5037



