// Agent agent1 in project MultiAgentProject

/* Initial beliefs and rules */

/* Initial goals */

!greet.

/* Plans */

+!greet : true   <- !setup;
				    .print("hello world.").

+!setup          <- joinRemoteWorkspace("server","localhost",_);
					.wait(1000)
					lookupArtifact("Env", Env);
				    focus(Env);
				    println("Ready").
				  
+focusOnTwitterLikeCommunity(CommunityName)
                 <- focusWhenAvailable(CommunityName);
               	    .print("prêt").
               	    
+postMessage(Message)
				 <- .print("Posting message");
				    .send(test_agent,achieve,message(Message))
				 .

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
