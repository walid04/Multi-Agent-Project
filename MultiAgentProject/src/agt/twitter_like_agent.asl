// Agent agent1 in project MultiAgentProject

/* Initial beliefs and rules */

/* Initial goals */

!greet.

/* Plans */

+!greet : true   <- !setup;
				    .myName(Name);
				    .concat("Hello from ",Name,Msg);
				    println(Msg).

+!setup          <- joinRemoteWorkspace("server","localhost",_);
				    focus("Env").
				  
+focusOnTwitterLikeCommunity(CommunityName)
               <- focus(CommunityName).

{ include("$jacamoJar/templates/common-cartago.asl") }
{ include("$jacamoJar/templates/common-moise.asl") }

// uncomment the include below to have an agent compliant with its organisation
//{ include("$moiseJar/asl/org-obedient.asl") }
