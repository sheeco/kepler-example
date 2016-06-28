package broadcast;

import java.util.List;

import com.example.experiment.ListActivity;
import com.example.experiment.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class record_broadcast {
	//��֪ͨ���㲥
	public void Shownotification(Context context) {
		 String ns = Context.NOTIFICATION_SERVICE;
		 NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);
		 int icon = R.drawable.background; //֪ͨͼ��
		 CharSequence tickerText = "Notice from Kepler"; //״̬����ʾ��֪ͨ�ı���ʾ
		 long when = System.currentTimeMillis(); //֪ͨ������ʱ�䣬����֪ͨ��Ϣ����ʾ
		 //����������Գ�ʼ�� Nofification
		 Notification notification = new Notification(icon,tickerText,when);
	     CharSequence contentTitle = "New location!"; //֪ͨ������
		 CharSequence contentText = "New sites needed to be signed!"; //֪ͨ������
		 Intent notificationIntent = new Intent(context,ListActivity.class); //�����֪ͨ��Ҫ��ת��Activity
		 PendingIntent contentIntent = PendingIntent.getActivity(context,0,notificationIntent,0);
		 notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		 mNotificationManager.notify(0,notification);// TODO Auto-generated method stub
			
	}
}