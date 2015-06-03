package dao;

import java.sql.ResultSet;

import connector.db.DB;
import connector.db.Field;
import connector.db.SelectReader;

public class Rating {
	/**
	 * Loads existent rating with given id
	 */
	public Rating(long rating_id) throws Exception {
		DB.Select("SELECT * from Ratings where id=" + rating_id, new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				if (rs.next()) {
					id = rs.getLong("id");
					positive = rs.getInt("positive");
					rating_user_nusp = rs.getLong("rating_user_nusp");
					description_id = rs.getLong("description_id");
				}
				else
					throw new Exception("Rating inexistente");
			}
		});
	}
	/**
	 * Create new Rating entry
	 */
	public Rating(long rating_user_nusp,long description_id) {
		// TODO: Check if rating with same rating_user_nusp and description_id doesn't exist already
		this.id = IDCOUNT;
		IDCOUNT++;
		this.rating_user_nusp = rating_user_nusp;
		this.description_id = description_id;
	}
	/**
	 * Save description in DB
	 */
	public void Save() {
		DB.InsertUpdate("Ratings", 
				true, 
				new Field<Long>("id",this.id,true),
				new Field<Integer>("positive",this.positive),
				new Field<Long>("rating_user_nusp",this.rating_user_nusp),
				new Field<Long>("description_id",this.description_id)
				);
	}
	
	/**
	 * Approve or disapprove description 
	 * @param value
	 */
	public void setPositive(int value){
		this.positive = value;
	}
	/**
	 * 
	 * @param description_id
	 * @return status = "approved" | "disapproved" | "not_done"
	 * @throws Exception
	 */
	public static String isApproved(long description_id) throws Exception{
		String status="not_done";
		DB.Select("SELECT COUNT(*) as count, positive from Ratings where description_id=" + description_id + " order by positive asc group by positive", new SelectReader() {
			public void Read(ResultSet rs) throws Exception
			{
				while (rs.next()) {
					if(rs.getInt("positive")==0 && rs.getInt("count") > 1) {
						status = "disapproved";
					} else if(rs.getInt("positive")==1 && rs.getInt("count") > 1){
						status = "approved";
					}
					
				} 
					throw new Exception("Nao ha rating para essa descriçao");
			}
		});
		return status;
	}
	
	
	
	private long id;
	// Positive = 0|1
	private int positive;
	private long rating_user_nusp;
	private long description_id;
	private static long IDCOUNT = 0;
}
