/*
Copyright (c) 2011, Kyle D. Sweeney
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package Advance;

import java.util.Iterator;

/**
 *
 * @author kdsweenx
 */
public class RadixSort {
    
    public static void sort(RadixSortable[] a, int maxNumber){
        boolean first=true;
        Buckets b=new Buckets();
        int exp=1;
        while(maxNumber/exp > 0){
            if(first){
                for(RadixSortable rs : a){
                    b.add((rs.getSortNum()/exp)%10, rs);
                }
                first=false;
            }else{
                for(Object o : b.getmaster()){
                    node rs=(node)o;
                    b.add((rs.getRadixSortable().getSortNum()/exp)%10, rs.getRadixSortable());
                }
            }
            b.merge();
            b.reset();
            exp*=10;
        }
        int i = 0;
        for (Object o : b.getmaster()) {
            node rs = (node) o;
            a[i]=rs.getRadixSortable();
            i+=1;
        }
    }
    
    private static class Buckets{
        linked_list master;
        linked_list[] buckets;
        public Buckets(){
            buckets=new linked_list[10];
            reset();
            master=buckets[0];
        }
        
        public void add(int i, RadixSortable a){
            buckets[i].give(new node(a));
        }
        
        public void reset(){
            for(int i=0; i< 10; ++i){
                buckets[i]=new linked_list();
            }
        }
        
        public void merge(){
            master=new linked_list(buckets[0]);
            for(int i=1; i<10; ++i){
                if(master.first == null){
                    master=new linked_list(buckets[i]);
                    continue;
                }
                master.merge(buckets[i]);
            }
        }
         public linked_list getmaster(){
             return master;
         }
    }
    
    private static class linked_list implements Iterable, Iterator{
        node first, last;
        public linked_list(){
            first=null;
            last=null;
        }
        public linked_list(linked_list l){
            this.first=l.first;
            this.last=l.last;
        }
        public void give(node o){
            if(first == null){
                first=o;
                last=o;
            }else{
                last.setNext(o);
                last=o;
            }
        }
        public void merge(linked_list l){
            if(l.first==null){
                int i=1;
                return;
            }
            last.setNext(l.first);
            
        }

        @Override
        public boolean hasNext() {
            return first!=null;
        }

        @Override
        public Object next() {
            node N=first;
            if(first==null){
                int i=0;
            }
            first=first.getNext();
            return N;
        }

        @Override
        public void remove() {
             //no.
        }

        @Override
        public Iterator iterator() {
            return this;
        }
    }
    
    private static class node{
        RadixSortable o;
        node next;
        
        public node(RadixSortable o){
            this.o=o;
        }
        public void setNext(node n){
            next=n;
        }
        public node getNext(){
            return next;
        }
        public RadixSortable getRadixSortable(){
            return o;
        }
        public int getRadix(){
            return o.getSortNum();
        }
    }
}
