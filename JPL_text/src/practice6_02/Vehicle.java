package practice6_02;

public class Vehicle{
	private double nowSpeed;
	private double direction;
	private String ownerName;

	private static int nextID = 0;
	private int vehicleID;
	
	enum TurnAngle{
		TURN_RIGHT(90.0),
		TURN_LEFT(270.0);
		
		private double angle;
		
		//enumコンストラクター
		private TurnAngle(double angle) {this.angle = angle;}
		
		/**
		 * 曲がる角度を返すメソッド
		 * @return angle：曲がる角度
		 */
		public double getAngle() {
			return this.angle;
		}
	}
	
	public Vehicle() {
		this.vehicleID = nextID;
		nextID++;
	}
	
	public Vehicle(String ownerName) {
		this();
		this.ownerName = ownerName;
	}
	
	public static int returnUsedMaxID() {
		return nextID - 1;
	}
	
	public String toString() {
		String desc = "nowSpeed = " + this.nowSpeed + "\n" + "direction = " + this.direction + "\n" + "ownewName = " + this.ownerName + "\n" + "ID番号 = " + this.vehicleID +/* "\n" + "次のID番号 = " + Vehicle.nextID + "\n" + "ID番号の最大値 = " + Vehicle.returnUsedMaxID() + */"\n";
		return desc;
	}
	public void changeSpeed(double speed) {
		this.nowSpeed = speed;
	}
	
	public void stop() {
		this.nowSpeed = 0.0;
	}
	
	public void turn(TurnAngle turnAngle) {
		System.out.print(turnAngle.getAngle() + "度曲がります" + "\n");
	}
	
	public double getNowSpeed() {return this.nowSpeed;}
	public void setNowSpeed(double speed) {
		this.nowSpeed = speed;
	}
	
	public double getDirection() {return this.direction;}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	public String getOwnerName() {return this.ownerName;}
	
	public int getNextID() {return nextID;}
	
	public int getVehicleID() {return this.vehicleID;}

	public static void main(String[] args) {
		Vehicle car = new Vehicle("Taguchi");
		car.setNowSpeed(60.0);
		car.setDirection(10.0);
		car.turn(TurnAngle.TURN_RIGHT);
		System.out.println("carの参照先インスタンスの内容は"+ "\n" + car);
		
		Vehicle bike = new Vehicle("Taguchi2");
		bike.setNowSpeed(30.0);
		bike.setDirection(20.0);
		bike.turn(TurnAngle.TURN_LEFT);
		System.out.println("bikeの参照先インスタンスの内容は"+"\n" + bike);
	}
}