// Agent user_agent in project MultiAgentProject

/* Initial beliefs and rules */

/* Initial goals */

!greet.

/* Plans */

+!greet : true   <- !setup;
				    .print("hello world.").

+!setup          <- joinRemoteWorkspace("server","localhost",_);
				    !setupArtifact.
				  
+!setupArtifact  <- .my_name(Name);
					.print(Name);
					makeArtifact(Name,"multiAgentProject.Env",[Name],Id);
				    focus(Id);
				    println("Ready").
//				    !createCommunity.
				    
//+!createCommunity
//                <- println("Creating community");
//               	   makeArtifact("TwitterLikeCommunity","multiAgentProject.TwitterLikeCommunityArtifact",[],Id);
//                   println("Artifact created").
				  
+createCommunity("TwitterLikeCommunity",CommunityName)
                <- println("Creating community");
               	   .my_name(Name);
               	   makeArtifact(CommunityName,"multiAgentProject.TwitterLikeCommunityArtifact",[CommunityName],Id);
//               	   .send(twitter_like_agent,achieve,focusOnEnv(Name, CommunityName));
				   !focusOnCommunity(CommunityName);
                   println("Artifact created").
				  
+!focusOnCommunity(CommunityName)
                 <- .print("focusing on community");
                 	focusWhenAvailable(CommunityName);
               	    .print("prêt").
                 
+joinCommunity(CommunityName)
			   <- println("Joining community");
				  lookupArtifact(CommunityName,Community);
				  focus(Community);
                  .println("Joined community").
                  
+postMessage(Message, Sender, Receiver)
			   <- .my_name(Name);
			   	  .print(Name);
			   	  .print(Sender);
			      if (Sender==Name) {
			   	  	println("yes");
			      }
				  else {
				  	println("no");
				  }
//			      if (Sender==Name) {
			   	  	println("Posting message");
			      	.send(Receiver,achieve,message(Message));
//			      }
			      .
			      
+!message(Message)
               <- println(Message).
			   	  
+deleteCommunity(communityName)
			    <- println("Deleting community");
			       lookupArtifact(CommunityName, Community);
			       stopFocus(Community).
                 
+leaveCommunity(communityName)
			    <- println("leaving community");
			       lookupArtifact(CommunityName, Community);
			       stopFocus(Community).
                 
+createCommunity3("ForumLikeCommunity",CommunityName)
               <- println("Creating community");
                  makeArtifact(CommunityName,"multiAgentProject.ForumLikeCommunityArtifact",[CommunityName],Id);
                  println("Artifact created for Forum").
			       
{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
