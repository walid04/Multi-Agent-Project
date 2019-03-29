// Agent test_agent in project multiAgentProject

/* Initial beliefs and rules */

/* Initial goals */

!greet.

/* Plans */

+!greet : true     <- /*!setup;*/
				      joinRemoteWorkspace("server","localhost",_);
				      .print("hello world.").

+!focusOnCommunity(CommunityName)
				   <- .my_name(Name);
					  print("Focusing on community");
					  .wait(1000);
					  lookupArtifact(CommunityName, Community);
				      focus(Community);
	               	  .print("prêt").
	               	  
+!joinCommunity(CommunityName)
				  <- lookupArtifact(CommunityName, Community);
				     focus(Community);
				     .print("Joined community").
	               	  
+!informUsersOfDeletedCommunity(CommunityName)
				  <- .print("The community ", CommunityName, " was deleted by the owner, plase consider leaving it as it has no longer an owner").

+!leaveCommunity(CommunityName)
                  <- lookupArtifact(CommunityName, Community);
                     stopFocus(Community);
                     .print("Left community").
                  
+postMessage(Message, Sender, Receiver)
			      <- .my_name(Name);
			         .concat(Sender,"",S);
			         .concat(Name,"",N);
			         if(N==S) {
			            .print("Sending post : ", Message, " to : ", Receiver);
			            .send(Sender,achieve,message(Message,Receiver,Sender));
			         }.
               	     
+!message(Message,Receiver,Sender)
                  <- .print("Posting message to twitter like community members");
                     .send(Receiver,achieve,post(Message,Sender)).
                     
+!post(Message,Sender)   
                  <- .print("Post received from : ", Sender, " is : ", Message).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
