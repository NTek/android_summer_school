/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\Android\\letnja skola\\letnja skola 2013\\workspace\\ServicesExamples\\src\\com\\rtrk\\service\\remote\\IRemoteServiceExample.aidl
 */
package com.rtrk.service.remote;
public interface IRemoteServiceExample extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.rtrk.service.remote.IRemoteServiceExample
{
private static final java.lang.String DESCRIPTOR = "com.rtrk.service.remote.IRemoteServiceExample";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.rtrk.service.remote.IRemoteServiceExample interface,
 * generating a proxy if needed.
 */
public static com.rtrk.service.remote.IRemoteServiceExample asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.rtrk.service.remote.IRemoteServiceExample))) {
return ((com.rtrk.service.remote.IRemoteServiceExample)iin);
}
return new com.rtrk.service.remote.IRemoteServiceExample.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_setMusicFinishedCallback:
{
data.enforceInterface(DESCRIPTOR);
android.os.IBinder _arg0;
_arg0 = data.readStrongBinder();
this.setMusicFinishedCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_startMusic:
{
data.enforceInterface(DESCRIPTOR);
this.startMusic();
reply.writeNoException();
return true;
}
case TRANSACTION_stopMusic:
{
data.enforceInterface(DESCRIPTOR);
this.stopMusic();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.rtrk.service.remote.IRemoteServiceExample
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void setMusicFinishedCallback(android.os.IBinder binder) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder(binder);
mRemote.transact(Stub.TRANSACTION_setMusicFinishedCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void startMusic() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_startMusic, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopMusic() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopMusic, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_setMusicFinishedCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_startMusic = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_stopMusic = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
}
public void setMusicFinishedCallback(android.os.IBinder binder) throws android.os.RemoteException;
public void startMusic() throws android.os.RemoteException;
public void stopMusic() throws android.os.RemoteException;
}
