package sqlite;
import interfaces.PatientManager;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.*;


public class SQLitePatientManager implements PatientManager
{
	private Connection c;

	public SQLitePatientManager(Connection c)
	{
		this.c = c;
	}

	@Override
	public List<Patient> searchPatientName(String patientName)
	{
		List <Patient> p = new ArrayList<Patient>();
		try
		{
			String sql="SELECT * FROM patient WHERE name LIKE ?";
			PreparedStatement prep=c.prepareStatement(sql);
			prep.setString(1,  "%" + patientName + "%");
			ResultSet rs=prep.executeQuery();
			while(rs.next())
			{
				int id=rs.getInt("ID");
				String name=rs.getString("Name");
				String address=rs.getString("Address");
				Date DOB=rs.getDate("DOB");
				Integer phone = rs.getInt("Phone");
				String email=rs.getString("Email");

				Patient pat = new Patient(id,name,address,DOB,phone,email);
				p.add(pat);
			}
			prep.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public int getLastId()
	{
		int id = 0;
		try
		{
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p = c.prepareStatement(query);
			ResultSet rs = p.executeQuery();
			id = rs.getInt("lastId");
			p.close();

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void addPatientandMedicalHistory (Patient p,MedicalHistory mh)
	{
		try
		{
			String sqlMedicalHistory="INSERT INTO medicalHistory (Name,DOB,Diseases,Allergies,Surgeries,WeightKg,HeightCm,ECG,EMG)"
					+  "VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps=c.prepareStatement(sqlMedicalHistory);
			ps.setString(1,mh.getName());
			ps.setDate(2,mh.getDOB());
			ps.setString(3,mh.getDiseases());
			ps.setString(4,mh.getAllergies());
			ps.setString(5, mh.getSurgeries());
			ps.setFloat(6,mh.getWeightKg());
			ps.setInt(7,mh.getHeightCm());
			//TODO a�adir el EMG y el ECG
			ps.executeUpdate();
			ps.close();

			int mhid=getLastId();
			String sqlpatient="INSERT INTO patient (Name, Address, DOB, Phone, Email,MHID)"
					+  "VALUES (?,?,?,?,?,?)";
			PreparedStatement prep=c.prepareStatement(sqlpatient);
			prep.setString(1,p.getName());
			prep.setString(2,p.getAddress());
			prep.setDate(3,p.getDob());
			prep.setInt(4, p.getPhoneNumber());
			prep.setString(5, p.geteMail());			
			prep.setInt(6, mhid);
			prep.executeUpdate();
			prep.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	

	@Override
	public Patient getPatient(Integer id) //returns the patient given the id
	{
		Patient patient =null;
		try
		{
			String sql="SELECT * from patient WHERE ID=?";
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			boolean patientCreated=false;
			Integer ptid=null;
			while (rs.next())
			{
				if(!patientCreated)
				{
					Integer patID=rs.getInt("ID");
					String name=rs.getString("Name");
					String address=rs.getString("Address");
					Date DOB=rs.getDate("DOB");
					Integer phone=rs.getInt("Phone");
					String email=rs.getString("Email");
				}
			}
			rs.close();
			ps.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return patient;
	}

	@Override
	public MedicalHistory getMedicalHistory(Patient p) //returns the medical history given a patient by its id
	{
		MedicalHistory mh=null;
		try
		{
			String sql="SELECT * FROM medicalHistory AS mh JOIN patient AS p ON mh.ID=p.MHID WHERE p.id=?";
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setInt(1, p.getId());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Integer id=rs.getInt(1);
				String name=rs.getString(2);
				Date dob=rs.getDate(3);
				String diseases=rs.getString(4);
				String allergies=rs.getString(5);
				String surgeries=rs.getString(6);
				Float weight=rs.getFloat(7);
				Integer height=rs.getInt(8);
				//TODO a�adir EMG ECG
				mh=new MedicalHistory(id,name,dob,diseases,allergies,surgeries,weight,height);
			}
			ps.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return mh;
	}


	@Override
	public Integer searchPatientByEmail(String email)
	{
		Integer patID = null;
		try
		{

			String sql = "SELECT ID FROM patient WHERE Email=?";
			PreparedStatement prepS = c.prepareStatement(sql);
			prepS.setString(1, email);
			ResultSet rs = prepS.executeQuery();
			while(rs.next())
			{
				patID = rs.getInt("ID");
			}
			prepS.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return patID;
	}

	@Override
	public boolean checkDoctor(Integer patID, Integer docID) 
	{
		ArrayList<Integer>docsIds = new ArrayList();
		boolean inTable = true;
		try
		{
			String sql = "SELECT * FROM PatientDoctor WHERE PATID=? AND DOCID=?";
			PreparedStatement prepS = c.prepareStatement(sql);
			prepS.setInt(1, patID);
			prepS.setInt(2, docID);
			ResultSet rs = prepS.executeQuery();
			while(rs.next())
			{
				Integer docId = rs.getInt("DOCID");
				docsIds.add(docId);
			}
			prepS.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		if(docsIds.isEmpty())
		{
			inTable = false;
		}
		return inTable;
	}

	@Override
	public void addDoctorToPatient(Patient pat, Doctor doc) 
	{
		try
		{
			String sql = "INSERT INTO PatientDoctor (PATID, DOCID)"
						+"VALUES (?,?)";
			PreparedStatement prepS = c.prepareStatement(sql);
			prepS.setInt(1, pat.getId());
			prepS.setInt(2, doc.getId());
			prepS.executeUpdate();
			prepS.close();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

    public List<Object> listTreatment(Patient ptnt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


