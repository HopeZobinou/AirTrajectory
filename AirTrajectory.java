/**
* AirTrajectory
*
* This program simulates projectile motion of an object. The program prompts the user for parameters of the object
* and launch parameters. It accounts for drag and air density, shows the details of how the object travels.
* When the object hits the ground it will show what happens to the object such as max distance traveled, landing velocity, and landing angle. 
*
*/
import java.util.Scanner;
import java.text.DecimalFormat;
public class AirTrajectory
{
   public static void main(String [] args)
   {
      double UserDiameter = 0;//m
      double UserMass = 0;//kg
      double UserDragCoefficient = 0;
      double UserLaunchAngle = 0;
      double UserLaunchHeight = 0;//m
      double UserLaunchVelocity = 0;//m/s
      boolean Flag = true;
      double AIR_DENSITY = 1.162; //kg/m^3
      double GRAVITY = 9.806; //m/s^2
      double Time = 0.0000;
      double Delta_T = 0.0100;
      double ApogeeTime = 0;
      double ymax = 0;
      String Choice;
      String Yes = "y";
      String No = "n";
      Scanner kb = new Scanner(System.in);
      Scanner kb2 = new Scanner(System.in);
      System.out.println("<*><*><*> Program AirTrajectory <*><*><*>");
      System.out.println();
      System.out.print("Enter Projectile Diameter (cm): ");
      UserDiameter = kb.nextDouble();
      System.out.print("Enter Projectile Mass (g): ");
      UserMass = kb.nextDouble();
      System.out.print("Enter Drag Coefficient: ");
      UserDragCoefficient = kb.nextDouble();
      System.out.println();
      System.out.print("Enter Launch Angle (degrees): ");
      UserLaunchAngle = kb.nextDouble();
      System.out.print("Enter Launch Height (cm): ");
      UserLaunchHeight = kb.nextDouble();
      System.out.print("Enter Launch Speed (m/s): ");
      UserLaunchVelocity = kb.nextDouble();
      System.out.println();
      System.out.print("Display details (y/n)? ");
      Choice = kb2.nextLine();
      System.out.println();
      System.out.println("3-2-1 ... Launch!");
      System.out.println();
      if(Choice.equals("y"))
      {
         System.out.println("Time      X-Accel    Y-Accel    X-Vel    Y-Vel    X-Pos    Y-Pos");
         System.out.println(" (s)      (m/s^2)    (m/s^2)    (m/s)    (m/s)     (m)      (m)");
         System.out.println("------    -------    -------    -----    -----    -----    -----");
      }
      UserDiameter = UserDiameter/100; 
      UserMass = UserMass/1000;
      double UserLaunchAngleRAD = UserLaunchAngle * Math.PI/180;
      UserLaunchHeight = UserLaunchHeight/100;
      double x_velocity = (UserLaunchVelocity) * Math.cos(UserLaunchAngleRAD);
      double y_velocity = (UserLaunchVelocity) * Math.sin(UserLaunchAngleRAD);
      double Radius = UserDiameter/2; //m
      double Area = Math.PI * Radius * Radius; 
      double x_position = 0.00;
      double y_position = UserLaunchHeight;
      double x_acceleration = 0.00;
      double y_acceleration = 0.00;
      while(Flag == true)
      {
         if(Choice.equals("y"))
         {
            System.out.printf("%1.4f %10.2f %10.2f %8.2f %8.2f %8.2f %8.2f\n", Time, x_acceleration, y_acceleration, x_velocity, y_velocity, x_position, y_position);
         }
         Time += 0.0100;
         double x_drag = 0.5 * UserDragCoefficient * (AIR_DENSITY) * Area * (x_velocity * x_velocity);
         double y_drag = 0.5 * UserDragCoefficient * (AIR_DENSITY) * Area * (y_velocity * y_velocity);
         x_acceleration = -(x_drag/UserMass);
         if(y_velocity > 0)
         {
            y_acceleration = -(y_drag/UserMass) - GRAVITY;//when upward
         }
         else
         {
            y_acceleration = (y_drag/UserMass) - GRAVITY;//when downward
         }
         x_velocity = x_velocity + (x_acceleration * Delta_T);
         y_velocity = y_velocity + (y_acceleration * Delta_T);
         x_position = x_position + (x_velocity * Delta_T);
         y_position = y_position + (y_velocity * Delta_T);
         if(y_position > ymax)
         {
            ymax = y_position;
            ApogeeTime = Time;
         }
         if(y_position < 0)
         {
            Flag = false;
         }     
      }
      if(Choice.equals("y"))
      {
         System.out.printf("%1.4f %10.2f %10.2f %8.2f %8.2f %8.2f %8.2f\n", Time, x_acceleration, y_acceleration, x_velocity, y_velocity, x_position, y_position);
      }
      System.out.println();
      double Impact_Velocity = Math.sqrt(Math.pow(x_velocity, 2)+Math.pow(y_velocity, 2));
      double Impact_Angle = Math.atan(y_velocity/x_velocity) * (180/Math.PI);
      DecimalFormat fmt = new DecimalFormat ("0.0000");
      DecimalFormat fmt2 = new DecimalFormat ("0.0");
      DecimalFormat fmt3 = new DecimalFormat ("0.00");
      System.out.println("Projectile Parameters ...");
      System.out.println("Diameter:      "+(UserDiameter*100)+" cm");
      System.out.println("Net Mass:      "+(UserMass*1000)+" g");
      System.out.println("Drag Coeff:    "+UserDragCoefficient);
      System.out.println();
      System.out.println("Launch Parameters ...");
      System.out.println("Launch Angle:      "+UserLaunchAngle+" deg");
      System.out.println("Launch Height:     "+fmt2.format((UserLaunchHeight*100))+" cm");
      System.out.println("Launch Speed:      "+UserLaunchVelocity+" m/s");
      System.out.println();
      System.out.println("Flight Summary ...");
      System.out.println("Flight Distance:   "+fmt3.format(x_position)+" m @ "+fmt.format(Time)+" sec");
      System.out.println("Flight Apogee:     "+fmt3.format(ymax)+" m @ "+fmt.format(ApogeeTime)+" sec");
      System.out.println("Impact Speed:      "+fmt3.format(Impact_Velocity)+" m/s");
      System.out.println("Impact Angle:      "+fmt2.format(Impact_Angle)+" deg");
      System.out.println();
      System.out.println("Additional Info ...");
      System.out.println("DELTA_T: "+fmt.format(Delta_T)+" sec");     
   }
}