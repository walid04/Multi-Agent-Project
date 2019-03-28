// Agent agent1 in project MultiAgentProject

/* Initial beliefs and rules */

/* Initial goals */

!greet.

/* Plans */

+!greet : true   <- !setup;
				    .print("hello world.").

+!setup          <- joinRemoteWorkspace("server","localhost",_);
					lookupArtifact("Env", Env);
				    focus(Env);
				    println("Ready").
				  
+focusOnTwitterLikeCommunity(CommunityName)
               <- focusWhenAvailable(CommunityName);
               	  .print("prêt").

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
