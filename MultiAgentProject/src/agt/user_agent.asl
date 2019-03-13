// Agent user_agent in project MultiAgentProject

/* Initial beliefs and rules */

/* Initial goals */

!greet.

/* Plans */

+!greet : true   <- !setup;
				    .myName(Name);
				    .concat("Hello from ",Name,Msg);
				    println(Msg).

+!setup          <- joinRemoteWorkspace("server","localhost",_);
				    !setupArtifact.
				  
+!setupArtifact  <- makeArtifact("Env","multiAgentProject.Env",[],Id);
				    focus(Id);
				    println("Ready").
				  
+createCommunity("TwitterLikeCommunity",CommunityName)
              <- makeArtifact(CommunityName,"multiAgentProject.TwitterLikeCommunityArtifact",[CommunityName],Id).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
