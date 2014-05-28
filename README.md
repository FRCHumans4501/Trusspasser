The Humans' 2014 Robot Code
===================

This repo contains the code used for Team 4501's 2014 robot, Trusspasser. The code is licensed under a GPL license, found in full in the LICENSE file.

Trusspasser is a robot made for the 2014 FRC season by Team 4501, The Humans. It runs on a mecanum drive train and uses a carwash-style intake and a catapult.

This code was written by Jerish Brown, Keith Kiyohara, Bryan Smith, and Jackson Spargur during the 2014 build season.

Contents
--------

The code is located in the src/ directory. The code should be clean enough to be readable by most programmers, but an update with thorough comments is necessary.

The robot runs through RobotMain.java. This class acts as the main class which contains references to the components and systems. We have two types of primary classes: Components and Systems. Components reference systems and control them, and systems control real-world systems (such as the drive train and the catapult).

### Components

#### Autonomous

This component is the only component running during the Autonomous section of the game. It lowers the lifter, drives forward, and shoots the ball.

#### DriveComponent

This controls the Drive Train and spin of the Lifter during Teleoperation.

#### PneumaticComponent

This controls the Compressor, Catapult, and position of the Lifter during Teleoperation. 

### Systems

#### Catapult

Interfaces with the Catapult Pneumatics. Contains methods to shoot the catapult at different strengths.

#### DriveTrain

Interfaces with the Drive Train. Uses PID control to control the mecanum drive. 

#### Vision

Experimental vision processing. Works, but it's slow.
