package manageTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List <Task> tasks = new ArrayList<>();
	private static int nextId = 1;
		
	public static void main(String[] args) {
		while(true) {
			System.out.println("以下から任意の選択肢を選んで下さい");
			System.out.println("1.タスクの作成");
			System.out.println("2.タスクの編集");
			System.out.println("3.タスクの表示");
			System.out.println("4.タスクの削除");
			System.out.println("5.終了");
			//選択肢の入力
			int input = new java.util.Scanner(System.in).nextInt();	
			//入力した結果に基づいて処理の分岐
			switch (input) {
				//タスク作成
				case 1: 
					System.out.println("タスクを作成します");
					createTask();
					break;
				//タスクをID経由で編集
				case 2:		
					System.out.println("IDに紐づくタスクを編集します");
					editTaskById();
					break;
				//タスクの一覧表示
				case 3:
					System.out.println("タスクの一覧を表示します");
					displayTasks();
					break;
				//ID経由でタスクの削除
				case 4:
					System.out.println("タスクを削除します");
					deleteTaskById();
					return;
				//プログラムの終了
				case 5:
					System.out.println("終了します");
					return;
				//選択肢以外の入力が行われた場合
				default:
					System.out.println("無効な操作です");			
			}
		}
	}
	//タスクを作成するメソッド
	private static void createTask() {
		Scanner scanner = new Scanner(System.in);
		//例外処理時のスコープ問題回避の為に変数宣言と初期化
		Priority priority = null;
		Status status = null;
		LocalDate dueDate = null;
		//要約の入力
		System.out.println("要約を入力して下さい");
		String summary = scanner.nextLine();
		//優先度の入力
		while(priority == null) {
		try {
			System.out.println("優先度を入力して下さい(Highest/High/Medium/Low)");
			priority = Priority.valueOf(scanner.nextLine());
		}catch(Exception e1) {
			System.out.println("エラーが発生しました、もう一度やり直してください(候補:Highest/High/Medium/Low)");
			}
		}
		//ステータスの入力
		while(status == null) {
		try {
			System.out.println("ステータスを入力して下さい(Done/Working/Cancel/ToDo/WaitingForSupport)");
			status = Status.valueOf(scanner.nextLine());
		}catch(Exception e2) {
			System.out.println("エラーが発生しました、もう一度やり直してください(候補:Done/Working/Cancel/ToDo/WaitingForSupport)");
			}
		}
		//期限の入力
		while(dueDate == null) {
		try {
			System.out.println("期限を入力して下さい(例：yyyy-mm-dd)");
			dueDate = LocalDate.parse(scanner.nextLine());
		}catch(Exception e3) {
			//入力中に発生するエラーに対する処理
			System.out.println("エラーが発生しました、もう一度やり直してください(例：yyyy-mm-dd)");
			}
		}
		//説明の入力
		System.out.println("説明を入力して下さい");
		String description = scanner.nextLine();
		//タスクの作成が完了したことを表示
		Task newTask = new Task(nextId++, summary, description, dueDate, priority, status);
		tasks.add(newTask);
		System.out.println("タスクを新規作成しました");
	}
	//ID経由でタスクを編集するメソッド
	private static void editTaskById() {
		Scanner scanner = new Scanner(System.in);
		//例外処理時のスコープ問題回避の為に変数宣言と初期化
		int id = 0;
		//IDを指定して編集
		System.out.println("編集したいタスクのIDを入力ください");
		try{
			id = scanner.nextInt();
		}catch(Exception e4) {
			System.out.println("エラーが発生しました、もう一度やり直してください");
		}
		Task task = findTaskById(id);
		if(task == null) {
			System.out.println("入力したタスクは存在しない様です");
			return;
		}
		//要約の編集
		System.out.println("新しい要約を入力してください（現在の要約: " + task.getSummary() + "）:");
	    task.setSummary(scanner.nextLine());
        try {
        	System.out.println("新しい優先度を入力してください(候補:Highest/High/Medium/Low)（現在の優先度: " + task.getPriority() + "）:");
        	task.setPriority(Priority.valueOf(scanner.nextLine()));
        }catch (Exception e5) {
			System.out.println("エラーが発生しました、もう一度やり直してください(候補:Highest/High/Medium/Low)");
		}
        //ステータスの編集
        try {
        	System.out.println("新しいステータスを入力してください(候補:Done/Working/Cancel/ToDo/WaitingForSupport)（現在のステータス: " + task.getStatus() + "）:");
        	task.setStatus(Status.valueOf(scanner.nextLine()));
        }catch(Exception e6) {
        	System.out.println("エラーが発生しました、もう一度やり直してください(候補:Done/Working/Cancel/ToDo/WaitingForSupport)");
        }
        //期限の編集
        try {
        	System.out.println("新しい期限を入力してください (例:YYYY-MM-DD)（現在の期限: " + task.getDueDate() + "）:");
        	task.setDueDate(LocalDate.parse(scanner.nextLine()));
        }catch(Exception e7) {
        	System.out.println("エラーが発生しました、もう一度やり直してください(例：yyyy-mm-dd)");
        }
        //説明の編集
        System.out.println("新しい説明を入力してください（現在の説明: " + task.getDescription() + "）:");
        task.setDescription(scanner.nextLine());
        System.out.println("タスクが更新されました。");
    }
	//タスクの全一覧を表示するメソッド
	private static void displayTasks() {
		for(Task task : tasks) {
			task.printTaskElements();
			System.out.println("---------------------");
		}
	}
	//IDからタスクを探すメソッド
	private static Task findTaskById(int id) {
		for(Task task : tasks) {
			if(task.getId() == id) {
				return task;
			}
		}
		return null;
	}
	//IDからタスクを削除するメソッド
	private static void deleteTaskById() {
        Scanner scanner = new Scanner(System.in);
      //例外処理時のスコープ問題回避の為に変数宣言と初期化
        int id = 0;
        try {
        	System.out.println("削除したいタスクのIDを入力してください");
        	id = scanner.nextInt();
        } catch(Exception e8) {
        	System.out.println("エラーが発生しました、入力したIDが存在するか確認してください");
        }
        	Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            System.out.println("タスクが削除されました");
        } else {
            System.out.println("指定されたIDのタスクが見つかりません");
        }
    }	
}
