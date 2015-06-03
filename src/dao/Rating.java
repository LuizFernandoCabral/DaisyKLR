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
					positive = rs.getBoolean("positive");
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
	public Rating(long rating_user_nusp, boolean positive, long description_id) {
		// TODO: Check if rating with same rating_user_nusp and description_id doesn't exist already
		this.id = 0;
		this.positive = positive;
		this.rating_user_nusp = rating_user_nusp;
		this.description_id = description_id;
	}
	/**
	 * Save description in DB
	 */
	public void Save() {
		if (this.id != 0)
			return;
		this.id = DB.InsertUpdate("Ratings", 
				true, 
				new Field<Long>("id",this.id,true),
				new Field<Boolean>("positive",this.positive),
				new Field<Long>("rating_user_nusp",this.rating_user_nusp),
				new Field<Long>("description_id",this.description_id)
		);
	}
	

	private long id = 0;
	private boolean positive;
	private long rating_user_nusp;
	private long description_id;
}
