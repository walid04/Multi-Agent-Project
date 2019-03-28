// Agent user_agent in project MultiAgentProject

/* Initial beliefs and rules */

/* Initial goals */

!greet.

/* Plans */

+!greet : true   <- !setup;
				    .print("hello world.").

+!setup          <- joinRemoteWorkspace("server","localhost",_);
				    !setupArtifact.
				  
+!setupArtifact  <- makeArtifact("Env","multiAgentProject.Env",[""],Id);
				    focus(Id);
				    println("Ready").
//				    !createCommunity.
				    
//+!createCommunity
//                <- println("Creating community");
//               	   makeArtifact("TwitterLikeCommunity","multiAgentProject.TwitterLikeCommunityArtifact",[],Id);
//                   println("Artifact created").
				  
+createCommunity("TwitterLikeCommunity",CommunityName)
                <- println("Creating community");
               	   makeArtifact(CommunityName,"multiAgentProject.TwitterLikeCommunityArtifact",[CommunityName],Id);
                   println("Artifact created").
                 
+createCommunity3("ForumLikeCommunity",CommunityName)
              <- println("Creating community");
               	 makeArtifact(CommunityName,"multiAgentProject.ForumLikeCommunityArtifact",[CommunityName],Id);
                 println("Artifact created for Forum").
                 
+deleteCommunity(communityName)
			    <- println("Deleting community");
			       lookupArtifact(CommunityName, Community);
			       stopFocus(Community).
                 
+leaveCommunity(communityName)
			    <- println("leaving community");
			       lookupArtifact(CommunityName, Community);
			       stopFocus(Community).
			       
{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
