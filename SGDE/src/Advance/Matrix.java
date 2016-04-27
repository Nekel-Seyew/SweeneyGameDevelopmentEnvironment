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

/**
 *
 * @author KyleSweeney
 */
public class Matrix {
    int row, col;
    double[][] data;
    
    public Matrix(double[]... data){
        this.data=data.clone();
        row=this.data.length;
        col=this.data[0].length;
    }
    
    public Matrix(int r, int c){
        data=new double[r][c];
        row=r;
        col=c;
    }
    /**
     * Returns a matrix which is the result of adding the two matricies together.
     * @param m the matrix to add to the calling matrix.
     * @return a new matrix which is the result of the calling matrix and the passed in matrix.
     * @throws Exception If the two matricies are not the same size, an Exception is thrown.
     */
    public Matrix add(Matrix m) throws Exception{
        if(this.row!=m.row || this.col!=m.col){
            throw new Exception("Matrix Sizes do not match");
        }
        Matrix q=new Matrix(row,col);
        for(int r=0; r<row; r++){
            for(int c=0; c<col; c++){
                q.place(r, c, data[r][c]+m.data[r][c]);
            }
        }
        return q;
    }
    /**
     * Adds the passed in matrix to the calling matrix.
     * @param m the matrix to add to this one.
     * @throws Exception If the two matricies are not the same size, an Exception is thrown 
     */
    public void addts(Matrix m)throws Exception{
        if(this.row!=m.row || this.col!=m.col){
            throw new Exception("Matrix Sizes do not match");
        }
        for(int r=0; r<row; r++){
            for(int c=0; c<col; c++){
                data[r][c]+=m.data[r][c];
            }
        } 
    }
    
    /**
     * Returns a new matrix which is the result of multiplying M by this matrix.
     * Matrix multiplication is treated like a transformation. The new matrix (C) is
     * the result of the matrix where the method is being called, (A) by the passed
     * in matrix (B). In other words, C=AB. OR, Matrix C=A.Multiply(B) == (C=AB)
     * @param m
     * @return 
     */
    public Matrix multiply(Matrix m) throws Exception{
        if(this.col!=m.row){
            throw new Exception("Matrix Size requirements not met");
        }
        Matrix q=new Matrix(this.row,m.col);
        for(int r=0; r<q.row; r++){
            for(int c=0; c<q.col; c++){
                q.place(r, c, multi(data,m.data,r,c));
            }
        }
        return q;
    }
    /**
     * This version acts like a function upon the passed in matrix. Just like
     * transformations, when an object calls this method, it becomes a function
     * for the passed in matrix. For example, if Matrix A calls this method, and
     * Matrix B is passed in, then A.multiplyts(B)=AB. This new value is then set
     * to B. In otherwords, A.multiplyts(B) == (B=AB).
     * @param m the matrix to be modified
     * @throws Exception if the matricies are not of proper sizes.
     */
    public void multiplyts(Matrix m) throws Exception{
        if(this.col!=m.row){
            throw new Exception("Matrix Size requirements not met");
        }
        Matrix q=new Matrix(this.row,m.col);
        for(int r=0; r<q.row; r++){
            for(int c=0; c<q.col; c++){
                q.place(r, c, multi(data,m.data,r,c));
            }
        }
        m=q;
    }
    
    /**
     * Returns a new matrix which is the scalar multiple of the matrix calling the method.
     * @param s the scalar multiple.
     * @return the resulting matrix which is a scalar multiple of the original.
     */
    public Matrix multiply(double s){
        double[][] q=data.clone();
        for(int i=0; i<q.length; i++){
            for(int j=0; j<q[i].length; j++){
                q[i][j]*=s;
            }
        }
        return new Matrix(q);
    }
    
    public double[] returnRow(int r){
        return data[r];
    }
    public double[] returnCol(int c){
        double[] r=new double[row];
        for(int i=0; i<data.length; i++){
            r[i]=data[i][c];
        }
        return r;
    }
    
    public void place(int r, int c, double value){
        data[r][c]=value;
    }
    
    @Override
    public Matrix clone(){
        Matrix q=new Matrix(data);
        return q;
    }
    
    private double multi(double[][] a, double[][] b, int r, int c){
        double ret=0;
        for(int k=0; k<a[0].length; k++){
            ret+=a[r][k]*b[k][c];
        }
        return ret;
    }
    
    @Override
    public String toString(){
        String s="";
        for(int r=0; r<data.length; r++){
            for(int c=0; c<data[r].length; c++){
                if((c+1)==data[r].length){
                    s+=data[r][c];
                    continue;
                }else{
                    s+=data[r][c]+" ";
                }
            }
            if((r+1)==data.length){
                break;
            }
            s+="\n";
        }
        return s;
    }
    
    public void transpose(){
        double[][] New=new double[col][row];
        for(int i=0; i<New.length; i++){
            New[i]=this.returnCol(i);
        }
        data=New;
    }
}
