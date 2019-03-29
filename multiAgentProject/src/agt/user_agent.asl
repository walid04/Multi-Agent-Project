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
				  
+createCommunity(CommunityName,TwitterLikeAgent)
                <- println("Creating community");
               	   .my_name(Name);
               	   .concat(CommunityName,"-",TwitterLikeAgent,Community);
               	   makeArtifact(CommunityName,"multiAgentProject.TwitterLikeCommunityArtifact",[Community],Id);
                   println("Artifact created");
    			   !focusOnCommunity(CommunityName,TwitterLikeAgent).
				  
+!focusOnCommunity(CommunityName,TwitterLikeAgent)
                <- .my_name(Name);
                   .send(TwitterLikeAgent,achieve,focusOnCommunity(CommunityName)).
                 
+joinCommunity(CommunityName,TwitterLikeAgent)
			    <- .send(TwitterLikeAgent,achieve,joinCommunity(CommunityName)).
			   	  
+informUsersOfDeletedCommunity(CommunityName,TwitterLikeAgent)
			    <- .send(TwitterLikeAgent,achieve,informUsersOfDeletedCommunity(CommunityName)).
                 
+leaveCommunity(CommunityName,TwitterLikeAgent)
			    <- .send(TwitterLikeAgent,achieve,leaveCommunity(CommunityName)).
+!message(Message)
               <- println(Message).
			       
{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
