//-----------------------------------------------------------------------------
//  List.java
//  Henry Chang
//  This is a List ADT that implements a node-based linked list format.
//  It has been transformed from a List ADT of int to that of Object. 
//-----------------------------------------------------------------------------


class List{

   private class Node{
      // Fields
      Object Data;
      Node next;
      Node prev;
      
      // Constructor
      Node(Object Data) { this.Data = Data; next = null; prev = null; }
      
      // toString():  overrides Object's toString() method
      public String toString() { 
         return String.valueOf(Data); 
      }
      
      // equals(): overrides Object's equals() method
      public boolean equals(Object x){
         boolean eq = false;
         Node that;
         if(x instanceof Node){
            that = (Node) x;
            eq = (this.Data==that.Data);
         }
         return eq;
      }
   }

   // Fields
   private Node front;
   private Node back;
   private Node current;
   private int len;
   private int cursor;

   // Constructor
   List() { 
      front = back = current = null; 
      len = 0; 
      cursor = -1;
   }


   // Access Functions --------------------------------------------------------

   // length()
   // Returns length of this List.
   int length() { 
      return len; 
   }
   
   // index()
   // If cursor is defined, returns the index of the cursor element,
   // otherwise returns -1
   int index(){
      return cursor;
   }

   // front() 
   // Returns front element.
   // Pre: length()>0
   Object front(){
      if( this.len == 0 ){
         throw new RuntimeException(
            "List Error: front() called on empty List");
      }
      return front.Data;
   }
   
   // back() 
   // Returns back element.
   // Pre: length()>0
   Object back(){
      if( this.len == 0 ){
         throw new RuntimeException(
            "List Error: back() called on empty List");
      }
      return back.Data;
   }
   
   // get()
   // Returns the element cursor is on
   // Pre: length()>0
   Object get(){
      if( this.len == 0 ){
         throw new RuntimeException(
            "List Error: get() called on empty List");
      }
      return current.Data;
   }

   // Manipulation Procedures -------------------------------------------------
   
   // clear()
   // Resets this List to its original empty state.
   void clear(){
      front = back = current = null;
      cursor = -1;
      len = 0;
   }
   
   // moveFront()
   // If List is non-empty, places the cursor under the front element,
   // otherwise does nothing.
   void moveFront(){
      if(len != 0){
         current = front;
         cursor = 1;
      }
   }
   
   // moveBack()
   // If List is non-empty, places the cursor under the back element,
   // otherwise does nothing.
   void moveBack(){
      if(len != 0){
         current = back;
         cursor = len;
      }
   }
   
   // movePrev()
   // If cursor is defined and not at front, moves cursor one step toward
   // front of this List, if cursor is defined and at front, cursor becomes
   // undefined, if cursor is undefined, does nothing.
   void movePrev(){
      if(cursor > 1 && cursor <= len){
         current = current.prev;
         cursor--;
      }else{
         cursor = -1;
         current = null;
      }
   }
   
   // moveNext()
   // If cursor is defined and not at back, moves cursor one step toward
   // back of this List, if cursor is defined and at back, cursor becomes
   // undefined, if cursor is undefined, does nothing.
   void moveNext(){
      if(cursor < len && cursor >= 1){
         current = current.next;
         cursor++;
      }else{
         cursor = -1;
         current = null;
      }
   }
   
   // prepend(Object Data)
   // Insert new element into this List. If List is non-empty,
   // insertion takes place before front element.
   void prepend(Object Data){
      Node N = new Node(Data);
      if( len != 0){
         N.next = front;
         front.prev = N;
         front = N;
      }else{
         front = back = N;
      }
      len++;
      cursor++;      
   }
   
   // append(Object Data)
   // Insert new element into this List. If List is non-empty,
   // insertion takes place after back element.
   void append(Object Data){
      Node N = new Node(Data);
      if( len != 0){
         N.prev = back;
         back.next = N;
         back = N;
      }else{
         front = back = N;
      }
      len++;
   }
   
   // insertBefore(Object Data)
   // Insert new element before cursor.
   // Pre: length()>0, index()>=1
   void insertBefore(Object Data){
      if(this.len == 0 || this.index() < 1){
         throw new RuntimeException(
            "List Error: insertBefore() called on empty List"
         );
      }
      Node N = new Node(Data);
      if(cursor > 1){
         N.next = current;
         current = current.prev;
         N.prev = current;
         current.next = N;
         current = N.next;
         current.prev = N;
      }else{
         N.next = front;
         front.prev = N;
         front = N;
      }
      cursor++;
      len++;
   }
   
   // insertAfter(Object Data)
   // Insert new element after cursor.
   // Pre: length()>0, index()>=1
   void insertAfter(Object Data){
      if(this.length() == 0 || this.index() < 1){
         throw new RuntimeException(
            "List Error: insertAfter() called on empty List"
         );
      }
      Node N = new Node(Data);
      if(cursor < len){
         N.prev = current;
         current = current.next;
         N.next = current;
         current.prev = N;
         current = N.prev;
         current.next = N;
      }else{
         N.prev = back;
         back.next = N;
         back = N;
      }
      len++;
   }
   
   // deleteFront()
   // Deletes the front element.
   // Pre: length()>0
   void deleteFront(){
      if(this.length() == 0){
         throw new RuntimeException(
            "List Error: deleteFront() called on empty List"
         );
      }         
      front = front.next;
      len--;
      if(cursor > 1)
         cursor--;
      else
         current = null;
		   cursor = -1;
   }
   
   // deleteBack()
   // Deletes the back element.
   // Pre:length()>0
   void deleteBack(){
      if(this.length() == 0){
         throw new RuntimeException(
            "List Error: deleteBack() called on empty List"
         );
      }
      back = back.prev;
      len--;
      if(cursor == len){
         cursor = -1;
         current = null;
      }
   } 

   // delete()
   // Deletes cursor element, making cursor undefined.
   // Pre: length()>0, index()>=0
   void delete(){
      if(this.len == 0 || this.cursor == -1){
         throw new RuntimeException(
            "List Error: deleteBack() called on empty List"
         );
      }
      Node temp = new Node(0);
      temp.prev = current.prev;
      temp.next = current.next;
      if(cursor > 1){
         current = temp.prev;
         current.next = temp.next;
      }else{
			front = front.next;
			front.prev = null;
		}
      if(cursor < len){
         current = temp.next;
         current.prev = temp.prev;
      }else{
			back = back.prev;
			back.next = null;
		}
      current = null;
      cursor = -1;
      temp = null;
      len--;      
   }      

   // Other Functions ---------------------------------------------------------

   // toString()
   // Overides Object's toString() method.
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = front;
      while(N!=null){
			sb.append(" ");
         sb.append(N.toString());
         N = N.next;
      }
      return new String(sb);
   }

   // equals()
   // Overrides Object's equals() method.  Returns true if x is a List storing
   // the same integer sequence as this List, false otherwise.
   public boolean equals(Object x){
      boolean eq  = false;
      List L;
      Node N, M;

      if(x instanceof List){
         L = (List)x;
         N = this.front;
         M = L.front;
         eq = (this.len==L.len);
         while( eq && N!=null ){
            eq = N.equals(M);
            N = N.next;
            M = M.next;
         }
      }
      return eq;
   }



}
