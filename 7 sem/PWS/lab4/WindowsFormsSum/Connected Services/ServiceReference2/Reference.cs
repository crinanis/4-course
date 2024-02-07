﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Этот код создан программой.
//     Исполняемая версия:4.0.30319.42000
//
//     Изменения в этом файле могут привести к неправильной работе и будут потеряны в случае
//     повторной генерации кода.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WindowsFormsSum.ServiceReference2 {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="A", Namespace="http://BKA/")]
    [System.SerializableAttribute()]
    public partial class A : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string sField;
        
        private int kField;
        
        private float fField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false)]
        public string s {
            get {
                return this.sField;
            }
            set {
                if ((object.ReferenceEquals(this.sField, value) != true)) {
                    this.sField = value;
                    this.RaisePropertyChanged("s");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=1)]
        public int k {
            get {
                return this.kField;
            }
            set {
                if ((this.kField.Equals(value) != true)) {
                    this.kField = value;
                    this.RaisePropertyChanged("k");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=2)]
        public float f {
            get {
                return this.fField;
            }
            set {
                if ((this.fField.Equals(value) != true)) {
                    this.fField = value;
                    this.RaisePropertyChanged("f");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://BKA/", ConfigurationName="ServiceReference2.SimplexSoap")]
    public interface SimplexSoap {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://BKA/Add", ReplyAction="*")]
        int Add(int x, int y);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://BKA/Add", ReplyAction="*")]
        System.Threading.Tasks.Task<int> AddAsync(int x, int y);
        
        // CODEGEN: Контракт генерации сообщений с именем s из пространства имен http://BKA/ не отмечен как обнуляемый
        [System.ServiceModel.OperationContractAttribute(Action="http://BKA/Concat", ReplyAction="*")]
        WindowsFormsSum.ServiceReference2.ConcatResponse Concat(WindowsFormsSum.ServiceReference2.ConcatRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://BKA/Concat", ReplyAction="*")]
        System.Threading.Tasks.Task<WindowsFormsSum.ServiceReference2.ConcatResponse> ConcatAsync(WindowsFormsSum.ServiceReference2.ConcatRequest request);
        
        // CODEGEN: Контракт генерации сообщений с именем a1 из пространства имен http://BKA/ не отмечен как обнуляемый
        [System.ServiceModel.OperationContractAttribute(Action="http://BKA/Sum", ReplyAction="*")]
        WindowsFormsSum.ServiceReference2.SumResponse Sum(WindowsFormsSum.ServiceReference2.SumRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://BKA/Sum", ReplyAction="*")]
        System.Threading.Tasks.Task<WindowsFormsSum.ServiceReference2.SumResponse> SumAsync(WindowsFormsSum.ServiceReference2.SumRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://BKA/AddS", ReplyAction="*")]
        int AddS(int x, int y);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://BKA/AddS", ReplyAction="*")]
        System.Threading.Tasks.Task<int> AddSAsync(int x, int y);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class ConcatRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="Concat", Namespace="http://BKA/", Order=0)]
        public WindowsFormsSum.ServiceReference2.ConcatRequestBody Body;
        
        public ConcatRequest() {
        }
        
        public ConcatRequest(WindowsFormsSum.ServiceReference2.ConcatRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://BKA/")]
    public partial class ConcatRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string s;
        
        [System.Runtime.Serialization.DataMemberAttribute(Order=1)]
        public double d;
        
        public ConcatRequestBody() {
        }
        
        public ConcatRequestBody(string s, double d) {
            this.s = s;
            this.d = d;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class ConcatResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="ConcatResponse", Namespace="http://BKA/", Order=0)]
        public WindowsFormsSum.ServiceReference2.ConcatResponseBody Body;
        
        public ConcatResponse() {
        }
        
        public ConcatResponse(WindowsFormsSum.ServiceReference2.ConcatResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://BKA/")]
    public partial class ConcatResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string ConcatResult;
        
        public ConcatResponseBody() {
        }
        
        public ConcatResponseBody(string ConcatResult) {
            this.ConcatResult = ConcatResult;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class SumRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="Sum", Namespace="http://BKA/", Order=0)]
        public WindowsFormsSum.ServiceReference2.SumRequestBody Body;
        
        public SumRequest() {
        }
        
        public SumRequest(WindowsFormsSum.ServiceReference2.SumRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://BKA/")]
    public partial class SumRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public WindowsFormsSum.ServiceReference2.A a1;
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=1)]
        public WindowsFormsSum.ServiceReference2.A a2;
        
        public SumRequestBody() {
        }
        
        public SumRequestBody(WindowsFormsSum.ServiceReference2.A a1, WindowsFormsSum.ServiceReference2.A a2) {
            this.a1 = a1;
            this.a2 = a2;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class SumResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="SumResponse", Namespace="http://BKA/", Order=0)]
        public WindowsFormsSum.ServiceReference2.SumResponseBody Body;
        
        public SumResponse() {
        }
        
        public SumResponse(WindowsFormsSum.ServiceReference2.SumResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://BKA/")]
    public partial class SumResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public WindowsFormsSum.ServiceReference2.A SumResult;
        
        public SumResponseBody() {
        }
        
        public SumResponseBody(WindowsFormsSum.ServiceReference2.A SumResult) {
            this.SumResult = SumResult;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface SimplexSoapChannel : WindowsFormsSum.ServiceReference2.SimplexSoap, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class SimplexSoapClient : System.ServiceModel.ClientBase<WindowsFormsSum.ServiceReference2.SimplexSoap>, WindowsFormsSum.ServiceReference2.SimplexSoap {
        
        public SimplexSoapClient() {
        }
        
        public SimplexSoapClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public SimplexSoapClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public SimplexSoapClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public SimplexSoapClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public int Add(int x, int y) {
            return base.Channel.Add(x, y);
        }
        
        public System.Threading.Tasks.Task<int> AddAsync(int x, int y) {
            return base.Channel.AddAsync(x, y);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        WindowsFormsSum.ServiceReference2.ConcatResponse WindowsFormsSum.ServiceReference2.SimplexSoap.Concat(WindowsFormsSum.ServiceReference2.ConcatRequest request) {
            return base.Channel.Concat(request);
        }
        
        public string Concat(string s, double d) {
            WindowsFormsSum.ServiceReference2.ConcatRequest inValue = new WindowsFormsSum.ServiceReference2.ConcatRequest();
            inValue.Body = new WindowsFormsSum.ServiceReference2.ConcatRequestBody();
            inValue.Body.s = s;
            inValue.Body.d = d;
            WindowsFormsSum.ServiceReference2.ConcatResponse retVal = ((WindowsFormsSum.ServiceReference2.SimplexSoap)(this)).Concat(inValue);
            return retVal.Body.ConcatResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<WindowsFormsSum.ServiceReference2.ConcatResponse> WindowsFormsSum.ServiceReference2.SimplexSoap.ConcatAsync(WindowsFormsSum.ServiceReference2.ConcatRequest request) {
            return base.Channel.ConcatAsync(request);
        }
        
        public System.Threading.Tasks.Task<WindowsFormsSum.ServiceReference2.ConcatResponse> ConcatAsync(string s, double d) {
            WindowsFormsSum.ServiceReference2.ConcatRequest inValue = new WindowsFormsSum.ServiceReference2.ConcatRequest();
            inValue.Body = new WindowsFormsSum.ServiceReference2.ConcatRequestBody();
            inValue.Body.s = s;
            inValue.Body.d = d;
            return ((WindowsFormsSum.ServiceReference2.SimplexSoap)(this)).ConcatAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        WindowsFormsSum.ServiceReference2.SumResponse WindowsFormsSum.ServiceReference2.SimplexSoap.Sum(WindowsFormsSum.ServiceReference2.SumRequest request) {
            return base.Channel.Sum(request);
        }
        
        public WindowsFormsSum.ServiceReference2.A Sum(WindowsFormsSum.ServiceReference2.A a1, WindowsFormsSum.ServiceReference2.A a2) {
            WindowsFormsSum.ServiceReference2.SumRequest inValue = new WindowsFormsSum.ServiceReference2.SumRequest();
            inValue.Body = new WindowsFormsSum.ServiceReference2.SumRequestBody();
            inValue.Body.a1 = a1;
            inValue.Body.a2 = a2;
            WindowsFormsSum.ServiceReference2.SumResponse retVal = ((WindowsFormsSum.ServiceReference2.SimplexSoap)(this)).Sum(inValue);
            return retVal.Body.SumResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<WindowsFormsSum.ServiceReference2.SumResponse> WindowsFormsSum.ServiceReference2.SimplexSoap.SumAsync(WindowsFormsSum.ServiceReference2.SumRequest request) {
            return base.Channel.SumAsync(request);
        }
        
        public System.Threading.Tasks.Task<WindowsFormsSum.ServiceReference2.SumResponse> SumAsync(WindowsFormsSum.ServiceReference2.A a1, WindowsFormsSum.ServiceReference2.A a2) {
            WindowsFormsSum.ServiceReference2.SumRequest inValue = new WindowsFormsSum.ServiceReference2.SumRequest();
            inValue.Body = new WindowsFormsSum.ServiceReference2.SumRequestBody();
            inValue.Body.a1 = a1;
            inValue.Body.a2 = a2;
            return ((WindowsFormsSum.ServiceReference2.SimplexSoap)(this)).SumAsync(inValue);
        }
        
        public int AddS(int x, int y) {
            return base.Channel.AddS(x, y);
        }
        
        public System.Threading.Tasks.Task<int> AddSAsync(int x, int y) {
            return base.Channel.AddSAsync(x, y);
        }
    }
}
