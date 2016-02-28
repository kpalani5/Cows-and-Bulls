# Cows-and-Bulls
Intellectual word game
					PROJECT ABSTRACT
                                                             
                                                                          COWS AND BULLS

OBJECTIVE

To create ‘COWS and BULLS’ game using JAVA (front end) and DATABASE (backend).
Cows and Bulls is a brain teaser game which involves guessing a randomly generated n-letter English word with distinct alphabets. These words are valid words.

METHODOLOGY			

Rules:

1.	The computer selects a word randomly from the database of valid words. The player is told the number of letters present in the word. (Say, a n-letter word)

2.	The player has to start guessing the word. Each guess has to be a n-letter valid word.

3.	Consider a valid word which has been selected by the computer. Let us refer to it as word1. It has n-letters and each letter in a specific position. Now consider the valid word guessed by the player. That too has n-letters. Each letter of the word guessed by the user can be :
i)	Present in word1 at the position specified by the user – A BULL
ii)	Present in word1 but at some other position – A COW
iii)	Absent in word1

4.	After each guess by the user, the computer shows the number of COWS and BULLS.

5.	Based on the output from each guess, the player has to guess the right word which was selected by the computer.

Difficulty levels:

	Based on the usage of the valid words in English, they are classified as common and obscure. 

1.	EASY LEVEL – Only common words are selected by the computer

2.	NORMAL LEVEL – All words are selected by the computer

3.	HARD LEVEL – Only obscure words are selected by the computer

Game Modes:

	The game has 4 modes.

1.	FREESTYLE MODE – No timer. No maximum number of turns. The player can take as many guesses as they want and for however long they want for finding the word.

2.	TIMER MODE – The player is provided with 3 minutes time. The player can make any number of guesses within this time frame to find the word.

3.	COUNTER MODE – The player is allowed 10 turns to guess the word.

4.	SEQUENCE MODE – The player is provided with 10 turns within 3 minutes to guess the word. If he guesses the word right, he can move on to the next word. The number of words guessed by the player during a sequence is noted and is stored in a leader board.


Word Levels:

	The game has 2 word levels.

1.	FOUR – LETTER WORDS

2.	FIVE – LETTER WORDS

Playing the game:

	The player has to select the difficulty, the word level and the game mode.
 He is then ready to play!

Example:

WORD selected by computer: RAIN

PLAYER:

GUESS 1: 		NICE			2 COWS 	0 BULLS

GUESS 2:		FOUL			0 COWS	0 BULLS

GUESS 3:		FAIL			0 COWS	2 BULLS

GUESS 4:		NAIL			1 COW		2 BULLS

GUESS 5:		PAIN			0 COWS	3 BULLS

GUESS 6:		RAIN			0 COWS	4 BULLS

WORD found in 6 guesses!










DATABASE IMPLEMENTATION


1.	Database for the 4 letter and 5 letter words taken from an English dictionary.

2.	The valid words are selected by using a program to eliminate words which have repeating letters.

3.	The words are classified as 4-letter or 5-letter words and as obscure or common words.

4.	A serial number is assigned to each word as a key.

5.	The range of each type of word is noted (4-letter common words, 4-letter obscure words, 5-letter common words, 5-letter obscure words).

6.	Based on the player’s choices of difficulty, word level and game mode, a random number of that range is generated and the word is selected. The user then plays the game based on the constraints decided by the game mode.

7.	The leader board for sequence mode asks for the player name once the player finishes the sequence. The 10 top sequences are stored in the leader board along with the player name.


USER INTERFACE

An interactive user interface has also been provided for easier gameplay.






							PROJECT BY:
							
P. KARTHIK PERUMAL

V. ANANDH

V.V. ARAVINDA KRISHNA
